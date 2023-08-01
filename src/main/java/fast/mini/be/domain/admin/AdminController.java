package fast.mini.be.domain.admin;

import fast.mini.be.global.erros.exception.Exception400;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static fast.mini.be.domain.admin.AdminRequest.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/order/update")
    public ResponseEntity<?> orderUpdate(@Valid @RequestBody orderUpdateDTO orderUpdateDTO){
        adminService.orderUpdate(orderUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/order/list/{status}")
    public ResponseEntity<?> orderUpdate(@PathVariable("status") String status, Pageable pageable) {
        if(!("wait".equals(status)) && !("complete".equals(status))){
            throw new Exception400("url","잘못된 입력입니다.");
        }

        Page<AdminResponse.OrderByStatusDTO> orderListByStatusDTO = adminService.orderListByStatus(status, pageable);
        return new ResponseEntity<>(orderListByStatusDTO,HttpStatus.OK);
    }
}