package org.jitterbit.connector;


public class Connection {

  private String accessToken;
  private String userName;
  
  public Connection(String accessToken, String userName) {
    this.accessToken = "token " + accessToken;
    this.userName = userName;
  }
  
}
