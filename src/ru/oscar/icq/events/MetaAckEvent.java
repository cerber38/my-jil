
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.meta.MetaAckParser;

public class MetaAckEvent extends EventObject {


	public MetaAckEvent(MetaAckParser source) {
		super(source);
	}
	
	public boolean isOk() {
		return ((MetaAckParser) getSource()).isOk();
	}
}
