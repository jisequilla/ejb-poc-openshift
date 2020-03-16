package com.sample.ejb;

import javax.ejb.Remote;

@Remote
public interface SampleBeanRemote {
     public String echo(String s);
 
}