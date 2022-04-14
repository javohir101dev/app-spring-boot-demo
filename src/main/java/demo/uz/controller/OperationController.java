package demo.uz.controller;

import demo.uz.domain.Operation;
import demo.uz.domain.User;
import demo.uz.model.OperationCrudDto;
import demo.uz.model.UserCrudDto;
import demo.uz.model.resp.ApiResponse;
import demo.uz.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @GetMapping("/get/{id}")
    public Operation get(@PathVariable(value = "id") Long id) {
        return operationService.get(id);
    }

    @GetMapping("/get/all")
    public Page<Operation> getList(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                   @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
        return operationService.getList(PageRequest.of(page, size));
    }

    @PostMapping("/save")
    public ApiResponse save(@RequestBody OperationCrudDto operationCrudDto) {
        return operationService.save(operationCrudDto);
    }

}
