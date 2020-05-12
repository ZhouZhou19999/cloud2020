import com.zxm.springcloud.PaymentMain8001;
import com.zxm.springcloud.entities.Payment;
import com.zxm.springcloud.serivce.PymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = PaymentMain8001.class)
@RunWith(SpringRunner.class)
public class test1 {
    @Autowired
    private PymentService pymentService;
    @Test
    public void test2(){
        Payment payment = pymentService.getPaymentById(1l);
        System.out.println(payment);
    }
    @Test
    public void test3(){
        Payment payment = new Payment();
        payment.setSerial("周雪明");
        int p = pymentService.create(payment);
        System.out.println(p);
    }

}
