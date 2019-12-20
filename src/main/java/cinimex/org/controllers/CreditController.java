package cinimex.org.controllers;

import cinimex.org.DTO.CreditDto;
import cinimex.org.services.CreditService;
import cinimex.org.utils.Response;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/credit")
@AllArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping
    public Response create(@RequestBody CreditDto creditDto) {
        return Response.success(creditService.create(creditDto));
    }

    @PutMapping("close")
    public Response closeCredit(@RequestParam("id") Long id) {
        return Response.success(creditService.close(id));
    }

    @GetMapping("/all")
    public Response allCredit() {
        return Response.success(creditService.findAll());
    }

    @GetMapping("/byFIO")
    public Response allCreditByFIO(@RequestParam("surname") String surname,
                                   @RequestParam("name") String name,
                                   @RequestParam("lastname") String lastname) {
        return Response.success(creditService.findAllByFIO(name, surname, lastname));
    }

    @GetMapping("/byInterval")
    public Response allCreditByInterval(@RequestParam(value = "fromDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date fromDate,
                                        @RequestParam(value = "toDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date toDate) {
        return Response.success(creditService.findForInterval(fromDate,toDate));
    }
    @GetMapping
    public Response allDangerous(){
        return Response.success(creditService.findDangerous());
    }
}
