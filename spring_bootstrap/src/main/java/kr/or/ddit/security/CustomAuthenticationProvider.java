package kr.or.ddit.security;

import java.sql.SQLException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIdException;
import com.jsp.service.MemberService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String login_id = (String) auth.getPrincipal(); // 로그인 시도한 ID를 가져온다
		String login_pwd = (String) auth.getCredentials(); // 로그인 시도한 Password 를 가져온다.
		
		
		try {
			memberService.login(login_id, login_pwd);
			
			MemberVO member = memberService.getMember(login_id);

			UserDetails authUser = new User(member);
			boolean invalidCheck = authUser.isAccountNonExpired() 
					&& authUser.isAccountNonLocked()
					&& authUser.isCredentialsNonExpired() 
					&& authUser.isEnabled();
			
			if (invalidCheck) {
				UsernamePasswordAuthenticationToken result
							= new UsernamePasswordAuthenticationToken(
										authUser.getUsername(), authUser.getPassword(), 
										authUser.getAuthorities());
				// 전달할 내용을 설정한 후
				result.setDetails(authUser);
				// 리턴한다. successHandler로 전송된다.
				return result;
				
			}
			
		} catch (NotFoundIdException e) {
			throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
		} catch (InvalidPasswordException e) {
			throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BadCredentialsException("서버 에러");
		}

		
		return null;
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}






