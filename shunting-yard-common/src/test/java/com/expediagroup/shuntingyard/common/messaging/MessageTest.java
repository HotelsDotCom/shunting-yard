/**
 * Copyright (C) 2016-2019 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.shuntingyard.common.messaging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.Before;
import org.junit.Test;

import com.expediagroup.shuntingyard.common.messaging.Message;

public class MessageTest {

  private final Message.Builder objectUnderTest = Message.builder();

  @Before
  public void setUp() {
    objectUnderTest.database("test_db");
    objectUnderTest.table("test_table");
    objectUnderTest.payload("test_payload");
    objectUnderTest.timestamp(1515585600000L);
  }

  @Test
  public void nullPayload() {
    objectUnderTest.payload(null);
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> objectUnderTest.build())
        .withMessage("Parameter 'payload' is required");
  }

  @Test
  public void emptyTable() {
    objectUnderTest.table("");

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> objectUnderTest.build())
        .withMessage("Parameter 'table' is required");
  }

  @Test
  public void typical() {
    Message message = objectUnderTest.build();

    assertThat(message).isNotNull();
    assertThat(message.getDatabase()).isEqualTo("test_db");
    assertThat(message.getPayload()).isEqualTo("test_payload");
    assertThat(message.getQualifiedTableName()).isEqualTo("test_db.test_table");
    assertThat(message.getTable()).isEqualTo("test_table");
    assertThat(message.getTimestamp()).isEqualTo(1515585600000L);
  }

}
