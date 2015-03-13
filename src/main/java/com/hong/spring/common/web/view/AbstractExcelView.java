package com.hong.spring.common.web.view;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.AbstractView;

import com.hong.spring.common.util.GenericUtils;

public abstract class AbstractExcelView<W extends Workbook> extends AbstractView {
	
	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	public AbstractExcelView() {
		setContentType(CONTENT_TYPE);
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		W workbook = (W) GenericUtils.getClassOfGenericTypeIn(getClass(), 0).newInstance();
		logger.debug("Created Excel Workbook from scratch");

		buildExcelDocument(model, workbook, request, response);

		response.setContentType(getContentType());

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
	}

	protected abstract void buildExcelDocument(
			Map<String, Object> model, W workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	protected Cell getCell(Sheet sheet, int row, int col) {
		Row sheetRow = sheet.getRow(row);
		if (sheetRow == null) {
			sheetRow = sheet.createRow(row);
		}
		Cell cell = sheetRow.getCell(col);
		if (cell == null) {
			cell = sheetRow.createCell(col);
		}
		return cell;
	}

	protected void setText(Cell cell, String text) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(text);
	}

}
