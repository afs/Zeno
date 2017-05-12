/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.apache.jena.atlas.logging.LogCtl ;
import org.apache.jena.query.Query ;
import org.apache.jena.query.QueryExecution ;
import org.apache.jena.query.QueryExecutionFactory ;
import org.apache.jena.query.QueryFactory ;
import org.apache.jena.rdf.model.Model ;
import org.apache.jena.rdf.model.ModelFactory ;
import org.openjdk.jmh.annotations.Benchmark;


public class MyBenchmark {

    static { LogCtl.setJavaLogging(); }
    
    static Model model = ModelFactory.createDefaultModel();
    static Query query = QueryFactory.create("ASK{}");
    
//    @Benchmark
//    public void testMethod1() {
//        Model m = ModelFactory.createDefaultModel();
//        Query q = QueryFactory.create("ASK{}");
//        try ( QueryExecution qExec = QueryExecutionFactory.create(q, m) ) {
//            qExec.execAsk();
//        }
//    }
//    @Benchmark
//    public void testMethod() {
//        Query q = QueryFactory.create("ASK{}");
//    }

    @Benchmark
    public boolean testMethod2() {
        Query q = QueryFactory.create("ASK{}");
        try ( QueryExecution qExec = QueryExecutionFactory.create(query, model) ) {
            boolean b = qExec.execAsk();
            return b;
        }
    }
    
//    @Benchmark
//    //@BenchmarkMode(Mode.Throughput)
//    //@OutputTimeUnit(TimeUnit.SECONDS)
//    public boolean testMethod3() {
//        try ( QueryExecution qExec = QueryExecutionFactory.create(query, model) ) {
//            boolean b = qExec.execAsk();
//            return b;
//        }
//        
//    }

}
