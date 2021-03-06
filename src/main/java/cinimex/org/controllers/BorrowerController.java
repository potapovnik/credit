package cinimex.org.controllers;

import cinimex.org.transfer_obj.BorrowerDto;
import cinimex.org.services.BorrowerService;
import cinimex.org.utils.Response;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrower")
@AllArgsConstructor

@PreAuthorize("hasAuthority('administrator') or hasAuthority('loan manager')")
public class BorrowerController {
    private final BorrowerService borrowerService;


    @PostMapping
    public Response create(@RequestBody BorrowerDto borrowerDto) {
        return Response.success(borrowerService.create(borrowerDto));
    }

}
