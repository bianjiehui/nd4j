package org.nd4j.linalg.api.ops.aggregates.impl;

import lombok.NonNull;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.aggregates.BaseAggregate;

/**
 *
 * PLEASE NOTE: This op is available for CPU only, and should NOT be ever called manually, unless you know why you're using it
 *
 * @author raver119@gmail.com
 */
public class AggregateGEMM extends BaseAggregate {

    public AggregateGEMM() {
        // no-op
    }

    public AggregateGEMM(@NonNull int M,@NonNull int N, @NonNull int K, @NonNull double alpha, @NonNull INDArray A, @NonNull int lda, @NonNull INDArray B, @NonNull int ldb, @NonNull double beta, @NonNull INDArray C, @NonNull int ldc) {
        this.arguments.add(A);
        this.arguments.add(B);
        this.arguments.add(C);

        this.indexingArguments.add(M);
        this.indexingArguments.add(N);
        this.indexingArguments.add(K);
        this.indexingArguments.add(lda);
        this.indexingArguments.add(ldb);
        this.indexingArguments.add(ldc);

        this.realArguments.add(alpha);
        this.realArguments.add(beta);
    }

    @Override
    public String name() {
        return "aggregate_gemm";
    }

    @Override
    public int opNum() {
        return 5;
    }

    @Override
    public int maxArguments() {
        return 3;
    }

    @Override
    public int maxShapes() {
        return 0;
    }

    @Override
    public int maxIntArrays() {
        return 0;
    }

    @Override
    public int maxIntArraySize() {
        return 0;
    }

    @Override
    public int maxIndexArguments() {
        return 6;
    }

    @Override
    public int maxRealArguments() {
        return 2;
    }

    @Override
    public int getSharedMemorySize() {
        return 0;
    }

    @Override
    public int getThreadsPerInstance() {
        return 0;
    }
}
