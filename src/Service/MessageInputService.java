package Service;

import java.util.ArrayList;

import Interface.IMessageInputType;
import Interface.ISampleMessageInputRepository;
import Interface.IMessageInputService;
import Repository.SampleMessageInputRepository;

/**
 * This case connects to MessageInputRepository for getting messages from repository.
 *
 */
public class MessageInputService implements IMessageInputService {
	ISampleMessageInputRepository messageRepository;
	
	public MessageInputService() {
		this.messageRepository = new SampleMessageInputRepository();
	}

	@Override
	public ArrayList<IMessageInputType> getMessageList() {
		this.messageRepository.generateMessageList();
		return this.messageRepository.getMessageInput();
	}

}
