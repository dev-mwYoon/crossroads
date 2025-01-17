package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.PointDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.MemberService;
import com.crossroads.app.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;

@Controller
@RequestMapping("/apply/*")
@RequiredArgsConstructor
@Slf4j
public class ApplyController {
    private final ApplyService applyService;
    private final MemberService memberService;
    private final PointService pointService;

    //연수신청 첫페이지
    @GetMapping("apply-first")
    public String formFirst(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("applyDTO", new ApplyDTO());
        HttpSession session = httpServletRequest.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        model.addAttribute("members", memberService.getMemberInfo(memberId));
        return "form/apply-first";
    }

    @PostMapping("apply-first/send")
    public RedirectView applyFirst(ApplyDTO applyDTO, Model model, HttpSession session, String applyCourse){
        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());
        model.addAttribute("applyCourse", applyCourse);
        log.info("apply-first : " + applyCourse);
        return new RedirectView("/apply/apply-second?applyCourse=" + applyCourse);
    }

    //연수신청 두번째 페이지
    @GetMapping("apply-second")
    public String applySecond (Model model, HttpServletRequest httpServletRequest, ApplyDTO applyDTO, @RequestParam(value="applyCourse") String applyCourse, RedirectAttributes redirectAttributes ) {
        log.info(applyDTO.toString());
        model.addAttribute("applyCourse", applyCourse);
        redirectAttributes.addFlashAttribute("applyCourse", applyCourse);
        HttpSession httpSession = httpServletRequest.getSession();
        log.info("apply-second getmapping : " + applyCourse);
        return "form/apply-second";
    }

    @PostMapping("apply-second")
    public RedirectView applySecondPost(HttpServletRequest httpServletRequest, Model model, ApplyDTO applyDTO, String applyCourse, RedirectAttributes redirectAttributes, PointDTO pointDTO){
        HttpSession httpSession = httpServletRequest.getSession();
       // httpSession.setAttribute("memberId", 2L);
        model.addAttribute("applyCourse", applyCourse);
        redirectAttributes.addFlashAttribute("applyCourse", applyCourse);
        applyDTO.setMemberId((Long)httpSession.getAttribute("memberId"));
        pointDTO.setMemberId((Long)httpSession.getAttribute("memberId"));
        applyService.saveApply(applyDTO);
        log.info(applyDTO.toString());
        log.info(pointDTO.toString());
        model.addAttribute("applyDTO", applyDTO);
        log.info("apply-second post 들어옴@@@@@@@@@@@@@@@@@@@@@@22");
        pointService.savePoint(pointDTO);
        return new RedirectView("/apply/apply-third");
    }

    //연수신청 세번째 페이지
    @GetMapping("/apply-third")
    public String formThird(Model model, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        return "form/apply-third";
    }

}
