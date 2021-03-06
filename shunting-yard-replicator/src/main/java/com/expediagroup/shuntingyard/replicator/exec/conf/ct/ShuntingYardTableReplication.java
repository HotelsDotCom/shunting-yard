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
package com.expediagroup.shuntingyard.replicator.exec.conf.ct;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.hotels.bdp.circustrain.api.conf.ReplicaTable;
import com.hotels.bdp.circustrain.api.conf.SourceTable;

public class ShuntingYardTableReplication {
  private @Valid @NotNull SourceTable sourceTable;
  private @Valid @NotNull ReplicaTable replicaTable;

  public SourceTable getSourceTable() {
    return sourceTable;
  }

  public void setSourceTable(SourceTable sourceTable) {
    this.sourceTable = sourceTable;
  }

  public ReplicaTable getReplicaTable() {
    return replicaTable;
  }

  public void setReplicaTable(ReplicaTable replicaTable) {
    this.replicaTable = replicaTable;
  }

  public String getReplicaDatabaseName() {
    SourceTable sourceTable = getSourceTable();
    ReplicaTable replicaTable = getReplicaTable();
    String databaseName = replicaTable.getDatabaseName() != null ? replicaTable.getDatabaseName()
        : sourceTable.getDatabaseName();
    return databaseName.toLowerCase();
  }

  public String getReplicaTableName() {
    SourceTable sourceTable = getSourceTable();
    ReplicaTable replicaTable = getReplicaTable();
    String tableNameName = replicaTable.getTableName() != null ? replicaTable.getTableName()
        : sourceTable.getTableName();
    return tableNameName.toLowerCase();
  }

}
