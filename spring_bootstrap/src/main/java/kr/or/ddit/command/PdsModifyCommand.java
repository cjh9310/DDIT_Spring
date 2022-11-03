package kr.or.ddit.command;

import java.util.List;

import com.jsp.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{

	private int pno;
	private List<Integer> deleteFile;
	
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public List<Integer> getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(List<Integer> deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public PdsVO toPdsVO(){
		PdsVO pds = super.toPdsVO();
		pds.setPno(pno);	
		
		return pds;
	}
	
}
