/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zeno;

import org.apache.jena.query.* ;

public class ActionQuery implements Action {

    private final Query query ;
    private final Dataset data ;

    public ActionQuery(String queryString, Dataset data) {
        this.query = genQuery(queryString);
        this.data = data ;
    }
    
    protected Query genQuery(String queryString) {
        return QueryFactory.create(queryString);
    }
    
    @Override
    public void perform() {
        try ( QueryExecution queryExecution = QueryExecutionFactory.create(query, data) ) {
            if ( query.isSelectType() )
                doSelectQuery(queryExecution) ;
            if ( query.isDescribeType() )
                doDescribeQuery(queryExecution) ;
            if ( query.isConstructType() )
                doConstructQuery(queryExecution) ;
            if ( query.isAskType() )
                doAskQuery(queryExecution) ;
        }
    }

    private static void doSelectQuery(QueryExecution queryExecution) {
        ResultSet rs = queryExecution.execSelect();
        ResultSetFormatter.consume(rs);
    }
    private static void doAskQuery(QueryExecution queryExecution) {
        queryExecution.execAsk();
    }

    private static void doConstructQuery(QueryExecution queryExecution) {
        queryExecution.execConstruct();
    }

    private static void doDescribeQuery(QueryExecution queryExecution) {
        queryExecution.execDescribe();
    }
}
