package server;

import java.util.Collection;
import java.util.List;

public interface Imatrix  {

        /**
         * @param index to be queried
         * @return the actual value it encapsulates
         */
        public Integer getValue(Index index);

        /**
         * @param index
         * @return a list of its neighboring indices.
         * Adjacency logic is delegated to implementing classes
         */
        public List<Index> getAdjacentIndices(Index index);


        public Collection<Index> getReachables(Index index);
}

