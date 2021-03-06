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
package com.expediagroup.shuntingyard.replicator.yaml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.expediagroup.shuntingyard.replicator.exec.external.CircusTrainConfig;

import com.hotels.bdp.circustrain.api.conf.OrphanedDataStrategy;
import com.hotels.bdp.circustrain.api.conf.ReplicationMode;

public class YamlFactoryTest {

  @Test
  public void typical() {
    String expectedYaml = new StringBuffer()
        .append("replica-catalog:\n")
        .append("  hive-metastore-uris: replicaMetaStoreUri\n")
        .append("  name: replica\n")
        .append("source-catalog:\n")
        .append("  disable-snapshots: false\n")
        .append("  hive-metastore-uris: sourceMetaStoreUri\n")
        .append("  name: source\n")
        .append("table-replications:\n")
        .append("- orphaned-data-strategy: HOUSEKEEPING\n")
        .append("  partition-fetcher-buffer-size: 1000\n")
        .append("  partition-iterator-batch-size: 1000\n")
        .append("  qualified-replica-name: replicadatabasename.replicatablename\n")
        .append("  replica-database-name: replicadatabasename\n")
        .append("  replica-table:\n")
        .append("    database-name: replicaDatabaseName\n")
        .append("    table-location: replicaTableLocation\n")
        .append("    table-name: replicaTableName\n")
        .append("  replica-table-name: replicatablename\n")
        .append("  replication-mode: FULL\n")
        .append("  replication-strategy: UPSERT\n")
        .append("  source-table:\n")
        .append("    database-name: databaseName\n")
        .append("    generate-partition-filter: true\n")
        .append("    partition-limit: 32767\n")
        .append("    qualified-name: databasename.tablename\n")
        .append("    table-name: tableName\n")
        .toString();
    StringWriter sw = new StringWriter();
    CircusTrainConfig circusTrainConfig = CircusTrainConfig
        .builder()
        .sourceMetaStoreUri("sourceMetaStoreUri")
        .replicaMetaStoreUri("replicaMetaStoreUri")
        .replication(ReplicationMode.FULL, "databaseName", "tableName", "replicaDatabaseName", "replicaTableName",
            "replicaTableLocation", OrphanedDataStrategy.HOUSEKEEPING)
        .build();
    Yaml yaml = YamlFactory.newYaml();
    yaml.dump(circusTrainConfig, sw);
    assertThat(sw.toString()).isEqualTo(expectedYaml);
  }

}
