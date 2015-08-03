/**
 *  Copyright 2011-2015 Bill Dortch / RunningReds.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.runningreds.horatio.model;

public abstract class ModelComponent {
    
    final Model schema;
    
    protected ModelComponent(Model schema) {
        this.schema = schema;
    }

    public Model getSchema() {
        return schema;
    }
    
    public String getSchemaName() {
        return schema == null ? null : schema.name;
    }
    
    public abstract String getQName();
    
    

}
