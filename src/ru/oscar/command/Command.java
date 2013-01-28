
package ru.oscar.command;

import ru.oscar.Flap;
import ru.oscar.core.Connect;

public interface Command {

    public void exec(Flap f);
   
    public void notify(Connect c);
    
}