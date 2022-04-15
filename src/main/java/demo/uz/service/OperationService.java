package demo.uz.service;

import demo.uz.domain.Operation;
import demo.uz.model.OperationCrudDto;
import demo.uz.model.ReportDto;
import demo.uz.model.resp.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Month;

public interface OperationService {

    Operation get(Long id);

    Page<Operation> getList(Pageable pageable);

    ApiResponse save(OperationCrudDto dto);

    ReportDto getReportCardMonthly(Long cardId, Month month);
}
