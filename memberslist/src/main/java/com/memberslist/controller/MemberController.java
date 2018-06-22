package com.memberslist.controller;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.memberslist.model.Member;
import com.memberslist.repository.MemberRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 
 * @author Administrator
 *
 */
@Component
@RestController
@ConfigurationProperties
@RequestMapping("/api")
public class MemberController{
	private static final String SUCCESS = "sucess";
	private static final String RESPONSE_DATA = "data";
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	
	@Autowired
	private MemberRepository memRepo;
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping( value = "/getMembers", method=RequestMethod.GET )
	public Map<String, Object> getMembers(){
		List<Member> members = memRepo.findAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put(SUCCESS, true);
		response.put(RESPONSE_DATA, members);
	  	logger.info("Fetching Member Details");
		return response;
	}
	
	/**
	 * 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/getMember/{memberId}", method=RequestMethod.GET)
	public Map<String, Object> getEmployee( @PathVariable("memberId") String memberId){
	
		Member memb = memRepo.findOne(memberId);
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		if(memb != null ){
			response.put(SUCCESS, true);
			response.put(RESPONSE_DATA, memb);
			logger.info("Member Details: "+ response.toString());
		} else{
			response.put(SUCCESS , false);
			response.put(RESPONSE_DATA, null);
			response.put("msg","No Data found");
			logger.info(response.toString());
		}
		
		return response;
	}
	
	/**
	 * 
	 * @param memb
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/createMember" , method = RequestMethod.POST)
	public Map<String, Object> createEmployee(@RequestBody  Map<String, Object> memb) throws ParseException{
		URL url;
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("dd.MM.yyyy");		
		String firstname = (memb.get("firstname") == null)?null:memb.get("firstname").toString();
		String lastname = (memb.get("lastname") == null)?null:memb.get("lastname").toString();
		int postalcode = (memb.get("postalcode") == null)?null:Integer.parseInt(memb.get("postalcode").toString());
		Date birthdate=	(memb.get("birhtdate") == null)?null:format.parse(memb.get("birhtdate").toString());
		File picture = (memb.get("picture") == null)?null:new File(memb.get("picture").toString());
				
		Member  newMem = new Member(firstname, lastname,birthdate, postalcode, picture);
				
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message" , "Member Records Created");
		response.put(SUCCESS, true);
		response.put(RESPONSE_DATA, newMem);
		memRepo.save(newMem);
		logger.info("Member Successfully Created"+response.toString());
		return response;
	}
	
	/**
	 * 
	 * @param memb
	 * @param memberId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/updateMember/{memberId}", method= RequestMethod.PUT)
	public Map<String, Object> updateEmployee(@RequestBody Map<String, Object> memb, @PathVariable("memberId") String memberId) throws ParseException{
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		Member oldMemb = memRepo.findOne(memberId);
		
		URL url;
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("dd.MM.yyyy");		
		String firstname = (memb.get("firstname") == null)?null:memb.get("firstname").toString();
		String lastname = (memb.get("lastname") == null)?null:memb.get("lastname").toString();
		Integer postalcode = (memb.get("postalcode") == null)?null:Integer.parseInt(memb.get("postalcode").toString());
		Date birthdate=	(memb.get("birhtdate") == null)?null:format.parse(memb.get("birhtdate").toString());
		File picture = (memb.get("picture") == null)?null:new File(memb.get("picture").toString());
		
		Member updatedMemb = new Member(firstname, lastname,birthdate, postalcode, picture);
		updatedMemb.setId(memberId);
		
		if(oldMemb != null){
			if(updatedMemb.getFirstname() == null)
				updatedMemb.setFirstname(oldMemb.getFirstname());
			if(updatedMemb.getLastname() == null)
				updatedMemb.setLastname(oldMemb.getLastname());
			if(updatedMemb.getBirthdate() == null)
				updatedMemb.setBirthdate(oldMemb.getBirthdate());
			if(updatedMemb.getPostalcode() == null)
				updatedMemb.setPostalcode(oldMemb.getPostalcode());
			if(updatedMemb.getPicture() == null)
				updatedMemb.setPicture(oldMemb.getPicture());
			
			response.put(SUCCESS, true);
			memRepo.save(updatedMemb);
			response.put(RESPONSE_DATA, updatedMemb);
		} else {
			response.put(SUCCESS, false);
			response.put(RESPONSE_DATA,"No Data Found to update");
		}
		return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteMember/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteMember(@PathVariable("id") String id){
		Member membObj = memRepo.findOne(id);
		Map<String , Object> response = new LinkedHashMap<String, Object>();
		
		if(membObj != null ){
			memRepo.delete(id);
			response.put(SUCCESS, true);
			response.put(RESPONSE_DATA, membObj);
			response.put("msg", "Document Deleted with Id:"+id);
			logger.info("Member Record Successfully Deleted"+response.toString());
		} else{
			response.put(SUCCESS , false);
			response.put(RESPONSE_DATA, null);
			response.put("msg","No Data found to delete");
			logger.info("Member Record Not Found!! "+response.toString());
		}
		return response;
	}
}
