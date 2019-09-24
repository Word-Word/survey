package com.spring.mybatisSurvey;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.mybatisSurvey.model.MybatisMember;
import com.spring.mybatisSurvey.model.MybatisMemberMapper;
import com.spring.mybatisSurvey.model.Q;

@Controller
@SessionAttributes("mybatisMember")
public class MybatisMemberController {	
	@Autowired
	MybatisMemberMapper mybatisMemberService;
	
	@RequestMapping("/inputForm")
	public void insertMember(Model model){
		model.addAttribute("mybatisMember", new MybatisMember());
		
		// ���� ����Ʈ ���
		ArrayList<Q> mybatisMembers2 = (ArrayList<Q>)mybatisMemberService.getAllMembers2();
		model.addAttribute("mybatisMembers2", mybatisMembers2);
	}//insertMember()
	
	
	@RequestMapping("/insertOk")
	public String insertOk(@Valid MybatisMember mybatisMember, BindingResult result){		
		if(result.hasErrors()){
			System.out.println("���� ���� �߻�");
//			return "redirect:inputForm";
			return "inputForm"; //forward ���
		} else {
			// ������ ������ DB�� ����
			mybatisMemberService.insertMember(mybatisMember);
			return "redirect:list";
		}
	}//insertOk()
	
	@RequestMapping("/modifyMember/{answernum}") //get��� ��ſ� ���������� �����ϴ� ��� ���
	public String modifyMember(@PathVariable int answernum, Model model){
		model.addAttribute("mybatisMember", mybatisMemberService.getMember(answernum));
		return "modifyForm";
	}
	
	@RequestMapping("/modifyOk")
	public String modifyOk(@Valid MybatisMember mybatisMember, BindingResult result ){
		
		if(result.hasErrors()){
			System.out.println("���� ���� �߻�");
			return "modifyForm";
		}else{
			mybatisMemberService.updateMember(mybatisMember);
			return "redirect:list";
		}
	}//modifyOk()
	
	@RequestMapping("/delMember")
	public void delMember(Model model){
		model.addAttribute("mybatisMember", new MybatisMember());
	}
	
	@RequestMapping("/delMember/{answernum}")
	public String delMember(@PathVariable int answernum, Model model){		
//		model.addAttribute("mybatisMember", new MybatisMember());
		
		//id ���� ���� üũ
		if(mybatisMemberService.getMember(answernum) != null){ //id �����ϴ� ���			
			model.addAttribute("mybatisMember", mybatisMemberService.getMember(answernum));
			return "delMember";
			
		} else { //id null�� ���
			return "delFail";
		}

	}//delMember
	
	
	@RequestMapping("/deleteOk")
	public String deleteOk(@Valid MybatisMember mybatisMember, BindingResult result){
		if(result.hasErrors()){
			System.out.println("���� ���� �߻�");
			return "delMember";
		} else {
			mybatisMemberService.deleteMember(mybatisMember.getAnswernum());
			return "redirect:list";
		}//if
	}//deleteOk()
	
	
	@RequestMapping("/list")
	public void list(Model model){
		ArrayList<MybatisMember> mybatisMembers = (ArrayList<MybatisMember>)mybatisMemberService.getAllMembers();
		model.addAttribute("mybatisMembers", mybatisMembers);
	}
}