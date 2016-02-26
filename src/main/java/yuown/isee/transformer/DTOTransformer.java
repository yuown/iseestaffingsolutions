package yuown.isee.transformer;

import yuown.isee.entity.BaseEntity;
import yuown.isee.model.Model;

import java.util.List;

public interface DTOTransformer<From extends Model, To extends BaseEntity<?>> {

    public List<To> transformFrom(List<From> sources);

    public To transformFrom(From source);

    public From transformTo(To source);

    public List<From> transformTo(List<To> sources);

}
