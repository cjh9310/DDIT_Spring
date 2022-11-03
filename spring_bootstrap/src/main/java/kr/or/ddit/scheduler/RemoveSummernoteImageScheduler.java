package kr.or.ddit.scheduler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.dao.BoardDAO;
import kr.or.ddit.dao.NoticeDAO;
import kr.or.ddit.dao.PdsDAO;

public class RemoveSummernoteImageScheduler {
	private NoticeDAO noticeDAO;
	private BoardDAO boardDAO;
	private PdsDAO pdsDAO;
	private String filePath;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	private static final Logger logger =
			LoggerFactory.getLogger(RemoveSummernoteImageScheduler.class);
	
	public void fileRemove() throws Exception {
		boolean existFile = false;
		
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		
		if(files!=null) for(File file : files) {	
			existFile = (noticeDAO.selectNoticeByImage(file.getName())!=null)
					|| (boardDAO.selectBoardByImage(file.getName())!=null)
					|| (pdsDAO.selectPdsByImage(file.getName())!=null);

			if(existFile) {
				logger.info(file.getName()+" 은 사용하는 파일입니다.");				
			}else {
				logger.info(file.getName()+" 은 사용하지 않습니다.");
				if(file.exists()) file.delete();
			}
			existFile=false;
		}
	}
}
