package cinimex.org.controllers;

import cinimex.org.transfer_obj.CreditDto;
import cinimex.org.services.CreditService;
import cinimex.org.utils.Response;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/credit")
@AllArgsConstructor
@PreAuthorize("hasAuthority('administrator') or hasAuthority('loan manager')")
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

    @GetMapping("/byFio")
    public Response allCreditByFio(
                                   @RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
                                   @RequestParam("lastName") String lastName) {
        return Response.success(creditService.findAllByFio(name, surname, lastName));
    }

    @GetMapping("/byInterval")
    public Response allCreditByInterval(@RequestParam(value = "fromDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date fromDate,
                                        @RequestParam(value = "toDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date toDate) {
        return Response.success(creditService.findForInterval(fromDate,toDate));
    }
    @GetMapping("/dangerous")
    public Response allDangerous(){
        return Response.success(creditService.findDangerous());
    }

    @GetMapping("byId")
    public Response findById(@RequestParam Long id){
        return Response.success(creditService.findById(id));
    }
}
