package com.minsait.devops.poc.greetings;
 
import javax.ejb.Stateless;
 
@Stateless(name="greetings")
public class  GreetingsBean implements Greetings  {
 
    @Override
    public String echo(String s) {
 
        return "こんにちは " +s;
    }
 
 
}