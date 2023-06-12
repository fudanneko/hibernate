package web.member.controller;

import web.member.pojo.MemberData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.member.util.MemberConstants.SERVICE;
@WebServlet("/login/login")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        MemberData memberData = json2Pojo(request, MemberData.class);
        if (memberData == null) {
            memberData = new MemberData();
            memberData.setMessage("無會員資訊");
            memberData.setSuccessful(false);
            writePojo2Json(response, memberData);
            return;
        }

        memberData = SERVICE.login(memberData);
        if (memberData.isSuccessful()) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            final HttpSession session = request.getSession();
            session.setAttribute("loggedin", true);
            session.setAttribute("memberData", memberData);
        }
        writePojo2Json(response, memberData);
    }
}
