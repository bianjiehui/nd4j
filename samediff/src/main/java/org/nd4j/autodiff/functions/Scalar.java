package org.nd4j.autodiff.functions;

import org.nd4j.autodiff.AbstractIdentityFactory;
import org.nd4j.autodiff.ArrayField;
import org.nd4j.autodiff.Field;
import org.nd4j.autodiff.opstate.OpState;
import org.nd4j.autodiff.samediff.SDGraph;
import org.nd4j.linalg.api.ops.impl.transforms.arithmetic.MulOp;


/**
 * Scalar value
 * @param <X>
 */
public class Scalar<X extends Field<X>> extends Constant<X> {

    protected double value;

    public Scalar(SDGraph graph,
                  double value,
                  AbstractIdentityFactory<X> i_factory) {
        this(graph, value, i_factory, false);

    }

    public Scalar(SDGraph graph,
                  double value,
                  AbstractIdentityFactory<X> i_factory
            ,boolean inPlace) {
        super(graph,i_factory.scalar(value),new int[]{1,1} ,i_factory,inPlace);
        this.value = value;

    }


    @Override
    public DifferentialFunction<X> mul(DifferentialFunction<X> i_v) {
        DifferentialFunction<X> dup = i_v.dup();
        if(i_v.getValue(true) instanceof ArrayField) {
            ArrayField arrayField = (ArrayField) i_v.getValue(true);
            addEdges(graph,
                    dup,
                    this,
                    new MulOp().name(),
                    OpState.OpType.TRANSFORM,
                    arrayField.getInput().getShape());
        }

        return dup;
    }


    @Override
    public DifferentialFunction<X> dup() {
        return new Scalar<>(graph, value,getM_factory());
    }
}
