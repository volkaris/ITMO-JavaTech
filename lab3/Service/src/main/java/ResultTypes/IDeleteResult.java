package ResultTypes;

import lombok.Getter;

public interface IDeleteResult {

    public class Success implements IDeleteResult {}

    public class Unsuccess implements IDeleteResult {
        public Unsuccess(String failReason) {
            this.failReason = failReason;
        }

        @Getter
        private String failReason;
    }
}
