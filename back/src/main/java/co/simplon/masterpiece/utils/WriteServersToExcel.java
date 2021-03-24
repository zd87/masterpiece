package co.simplon.masterpiece.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.ListUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import co.simplon.masterpiece.dtos.AttributeViewDto;
import co.simplon.masterpiece.dtos.ServerViewDto;

@Component
public class WriteServersToExcel {

	private XSSFWorkbook workbook;

	private XSSFSheet sheet;

	private List<ServerViewDto> listServers;

	private List<String> attrHeaders;

	private List<String> defaultHeaders = Arrays.asList("Name", "Full name", "Country", "Perimeter",
			"IP");

	public WriteServersToExcel(List<ServerViewDto> listServers, List<String> attrHeaders) {
		this.listServers = listServers;
		this.attrHeaders = attrHeaders;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Servers");
		Row row = sheet.createRow(0);
		CellStyle style = setHeaderStyle();
		List<String> headers = ListUtils.union(defaultHeaders, attrHeaders);
		int columnCount = 0;
		for (String header : headers) {
			createCell(row, columnCount++, header, style);
		}
	}

	private void writeDataLines() {
		int rowCount = 1;
		CellStyle style = setRowStyle();

		for (ServerViewDto server : listServers) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, server.getName(), style);
			createCell(row, columnCount++, server.getFullName(), style);
			createCell(row, columnCount++, server.getCountry().getName(), style);
			createCell(row, columnCount++, server.getPerimeter().getName(), style);
			createCell(row, columnCount++, server.getIp().getIp(), style);
			for (String header : attrHeaders) {
				String value = attributeValue(server.getAttributes(), header);
				createCell(row, columnCount++, value, style);
			}
		}
	}

	private String attributeValue(List<AttributeViewDto> attributes, String header) {
		String value = "";
		for (AttributeViewDto attribute : attributes) {
			if (attribute.getName().equals(header)) {
				value = attribute.getValue();
			}
		}
		return value;
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		cell.setCellValue(String.valueOf(value));
		cell.setCellStyle(style);
	}

	private CellStyle setHeaderStyle() {
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(13);
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}

	private CellStyle setRowStyle() {
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(11);
		style.setFont(font);
		return style;
	}
}
