package web.member.controller;

import web.member.dao.impl.MemberDataDaoImpl;
import web.member.pojo.MemberData;
import web.member.service.impl.MemberServiceImpl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import static core.util.CommonUtil.json2Pojo;
import static web.member.util.MemberConstants.SERVICE;


    @WebServlet("/DBGifReaderController")
public class DBGifReaderController extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

            res.setContentType("image/gif");
            ServletOutputStream out = res.getOutputStream();

            MemberDataDaoImpl dao = new MemberDataDaoImpl();
            try {
                Integer memberNoA = Integer.valueOf(req.getParameter("memberNo"));
                MemberData thismemberNo = dao.selectById(memberNoA);
                byte[] thisPic = thismemberNo.getMemberPic();

                if (thisPic != null) {
                    out.write(thisPic);
                    System.out.println("把查到的物件的照片放上去" );
                } else {
                    out.write(getImageBytes("/WEB-INF/member/image/gray.jpg"));
                    System.out.println("把預設的照片放上去" );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private byte[] getImageBytes(String imageUrl) throws IOException {
            InputStream in = getServletContext().getResourceAsStream(imageUrl);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
