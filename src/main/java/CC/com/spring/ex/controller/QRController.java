package CC.com.spring.ex.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class QRController {

    static String QRPW;

    @RequestMapping(value = "/qr")
    protected void renderMergedOutputModel(HttpServletResponse res) throws Exception {
        res.setContentType("image/png; charset=UTF-8");
        res.setHeader("Content-Transfer-Encoding", "binary");
        QRCodeWriter qr = new QRCodeWriter();

        ServletOutputStream out = res.getOutputStream();

        //코드
        int code = (int) (Math.random() * 65536);
        String text = Integer.toString(code);
        QRPW = text;

        //qr에 코드 입력
        BitMatrix bitMatrix = qr.encode(text, BarcodeFormat.QR_CODE, 300, 300);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", out);

        //이미지 출력

        out.flush();
    }
}