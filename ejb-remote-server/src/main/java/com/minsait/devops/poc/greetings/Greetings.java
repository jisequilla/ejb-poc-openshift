package com.minsait.devops.poc.greetings;

import javax.ejb.Remote;

@Remote
public interface Greetings {
     public String echo(String s);
 
}