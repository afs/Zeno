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

import java.util.Collections ;
import java.util.List ;

import org.apache.jena.query.Dataset ;
import org.apache.jena.query.DatasetFactory ;

public class Benchmark {

    public static void main(String[] args) {
        
        List<Action> actions = setup();
        
        int warmN = 10_000_000;
        
        warmup(warmN, actions);
        
        long tStart = getTime();
        
        int N = 100_000;
        timedRun(N, actions);
        
        long tFinish = getTime();
        
        // Even if negative origin.
        long tElapsed = tFinish-tStart;
        double tElapsedSeconds = tElapsed/(1000.0*1000.0*1000.0);
        double ops = N/tElapsedSeconds;
        
        System.out.printf("Time: %.3fs\n", tElapsed/(1000.0*1000.0*1000.0));
        System.out.printf("Ops:  %.0f ops/s\n", ops);
        
    }

    
    private static List<Action> setup() {
        Dataset ds = DatasetFactory.create();
        Action a = new ActionQuery("SELECT * {}", ds);
        return Collections.singletonList(a); 
    }


    private static void timedRun(int N, List<Action> actions) {
        for ( int i = 0 ; i < N ; i++ ) {
            actions.forEach(Action::perform);
        }
    }

    private static void warmup(int N, List<Action> actions) {
        for ( int i = 0 ; i < N ; i++ ) {
            actions.forEach(Action::perform);
        }
    }


    public static long getTime() {
        return System.nanoTime();
    }
}
