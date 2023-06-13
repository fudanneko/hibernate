package web.member.controller;

import web.member.pojo.MemberData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Base64;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.member.util.MemberConstants.SERVICE;
@WebServlet("/member/getmemberdata")
public class getmemberdataServlt extends HttpServlet {
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


        memberData = SERVICE.selectById(memberData);
        if (memberData.isSuccessful()) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            if(memberData.getMemberPic()!= null){
                String MemberPic=Base64.getEncoder().encodeToString(memberData.getMemberPic());
                memberData.setMemberPic4json(MemberPic);
            }
        }
        writePojo2Json(response, memberData);
    }
}
