package co.simplon.masterpiece.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.masterpiece.dtos.AttributeDto;
import co.simplon.masterpiece.dtos.CountryDto;
import co.simplon.masterpiece.dtos.IpDto;
import co.simplon.masterpiece.dtos.PerimeterDto;
import co.simplon.masterpiece.dtos.ServerDto;

public class ReadServersFromExcel {

	private XSSFWorkbook workbook;

	private XSSFSheet sheet;

	private List<String> defaultHeaders = Arrays.asList("Name", "Full name", "Country", "Perimeter",
			"IP");

	private List<String> headers;

	private List<ServerDto> extractedServers;

	DataFormatter formatter;

	public ReadServersFromExcel() {
		headers = new ArrayList();
		extractedServers = new ArrayList();
		formatter = new DataFormatter();
	}

	public List<ServerDto> export(MultipartFile file) throws IOException {
		FileInputStream fis = (FileInputStream) file.getInputStream();
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		readHeadline();
		readDatalines();
		workbook.close();
		fis.close();

		return extractedServers;
	}

	private void readHeadline() {
		Row headline = sheet.getRow(0);
		for (Cell cell : headline) {
			headers.add(cell.getStringCellValue());
		}
	}

	private void readDatalines() {
		int rowCount = 1;
		for (Row row : sheet) {
			if (row == sheet.getRow(0))
				continue;
			extractedServers.add(convertRowToServer(row));
		}

		/* Alternative iteration */
//		Iterator<Row> rowIt = sheet.iterator();
//		while (rowIt.hasNext()) {
//			Row row = rowIt.next();
//			
//		}
	}

	private ServerDto convertRowToServer(Row row) {
		ServerDto serverDto = new ServerDto();
		List<AttributeDto> attributes = new ArrayList();
		for (int i = 0; i < headers.size(); i++) {
			String cellValue = formatter.formatCellValue(row.getCell(i));
			switch (headers.get(i)) {
			case "Name":
				serverDto.setName(cellValue);
				break;
			case "Full name":
				serverDto.setFullName(cellValue);
				break;
			case "Country":
				serverDto.setCountry(new CountryDto(cellValue));
				break;
			case "Perimeter":
				serverDto.setPerimeter(new PerimeterDto(cellValue));
				break;
			case "IP":
				serverDto.setIp(new IpDto(cellValue));
				break;
			default:
				if (!StringUtils.isEmpty(cellValue))
					attributes.add(new AttributeDto(headers.get(i), cellValue));
			}
			serverDto.setAttributes(attributes);
		}
		/* Validate inputs */
		Validator javaxValidator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ServerDto>> errors = javaxValidator.validate(serverDto);

		return serverDto;
	}

}
