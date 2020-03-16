package com.sample.ejb;
 
import javax.ejb.Stateless;
 
@Stateless(name="greetings")
public class  SampleBeanRemoteImpl implements SampleBeanRemote  {
 
    @Override
    public String echo(String s) {
 
        return "こんにちは " +s;
    }
 
 
}