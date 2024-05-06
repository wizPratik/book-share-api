package com.book_share.api.exchange_request.api;

import com.book_share.api.base.api.BaseController;
import com.book_share.api.exchange_request.model.ExchangeRequestDTO;
import com.book_share.api.exchange_request.service.ExchangeRequestService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(
    value = "/api/exchangeRequests",
    produces = MediaType.APPLICATION_JSON_VALUE)
public final class ExchangeRequestController implements BaseController {

    private final ExchangeRequestService exchangeRequestService;

    public ExchangeRequestController(
        final ExchangeRequestService exchangeRequestService1) {
        this.exchangeRequestService = exchangeRequestService1;
    }

    @GetMapping
    public ResponseEntity<List<ExchangeRequestDTO>> getAllExchangeRequests() {
        return ResponseEntity.ok(exchangeRequestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRequestDTO> getExchangeRequest(
        @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(exchangeRequestService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createExchangeRequest(
        @RequestBody @Valid final ExchangeRequestDTO exchangeRequestDTO) {
        final Long createdId =
            exchangeRequestService.create(exchangeRequestDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateExchangeRequest(
        @PathVariable(name = "id") final Long id,
        @RequestBody @Valid final ExchangeRequestDTO exchangeRequestDTO) {
        exchangeRequestService.update(id, exchangeRequestDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteExchangeRequest(
        @PathVariable(name = "id") final Long id) {
        exchangeRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
