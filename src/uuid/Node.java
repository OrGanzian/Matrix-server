package uuid;

import com.sun.istack.internal.NotNull;

import java.util.Collection;

public interface Node extends HasUUID{

    Collection<Node> getCollection(@NotNull final Class<? extends HasUUID> classType);
}
