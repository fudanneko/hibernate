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
            System.out.println(memberNoA);
            out.write(dao.selectById(memberNoA).getMemberPic());
        } catch (Exception e) {
            InputStream in = getServletContext().getResourceAsStream("/webapp/WEB-INF/login/image/JAMIGO_LOGO.gif");
            byte[] buf = new byte[in.available()];
            in.read(buf);
            out.write(buf);
            in.close();
        }
    }

}
