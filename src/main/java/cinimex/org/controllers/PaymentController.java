package cinimex.org.controllers;

import cinimex.org.transfer_obj.PaymentDto;
import cinimex.org.services.PaymentService;
import cinimex.org.utils.Response;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
@PreAuthorize("hasAuthority('administrator') or hasAuthority('loan manager')")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payOffDebt")
    public Response payOffDebt(@RequestBody PaymentDto paymentDto) {
        return Response.success(paymentService.payOffDebt(paymentDto));
    }

    @PostMapping("/schedulePayment")
    public Response schedulePayment(@RequestBody PaymentDto paymentDto) {
        return Response.success(paymentService.schedulePayment(paymentDto));
    }
}
