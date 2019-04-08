/*
 * Copyright © 2018 Jitterbit, Inc.
 *
 * Licensed under the JITTERBIT MASTER SUBSCRIPTION AGREEMENT
 * (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * https://www.jitterbit.com/cloud-eula
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

package org.jitterbit.connector.github;

import org.jitterbit.connector.sdk.Connection;
import java.util.Objects;

/**
 * Connection to a GitHub endpoint. Uses the
 * <a href="https://developer.github.com/v3/repos/contents/"
 * target="_blank">Official GitHub REST v3 API</a>.
 */
public class GitHubConnection implements Connection {

  private String accessToken;
  private String userName;
  private GitHubClient client;

  /**
   * Constructs a GitHub connection using a GitHub access token.
   *
   * @param accessToken GitHub Personal access tokens that gives you the ability to make
   * GitHub API calls
   */
  public GitHubConnection(String accessToken, String userName) {
    this.accessToken = "token " + accessToken;
    this.userName = userName;
  }

  /**
   * Opens a GitHub version 2 connection.
   */
  @Override
  public void open() throws ConnectionException {
    client = new GitHubClient(accessToken, userName);
    try {
      client.testConnection();
    } catch (Exception e) {
      throw new ConnectionException(e.getMessage(), "Unauthorized Access", e);
    }
  }

  /**
   * Returns the Slack client of the connection. If there is no client, opens
   * a new Slack connection and returns the client.
   *
   * @return the Slack client
   */
  public GitHubClient getClient() throws Exception {
    if (Objects.isNull(client)) {
      open();
    }
    return this.client;
  }

  /**
   * Closes the GitHub connection.
   */
  @Override
  public void close() {
    client = null;
  }
  
}
