package Database;

public interface ICommand {
    String sql();
    String[] columns();
    Object[] values();
}
