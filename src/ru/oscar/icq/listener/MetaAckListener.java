
package ru.oscar.icq.listener;

import java.util.EventListener;
import ru.oscar.icq.events.MetaAckEvent;

public interface MetaAckListener extends EventListener {
	
	public void onMetaAck(MetaAckEvent  e);
	
}
