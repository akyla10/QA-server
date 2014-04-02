package gameClasses;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by anton on 4/2/14.
 */
public class SnapshotTest {

    @Test
    public void SnapshotTestBlack(){
        Field[][] field = new Field[5][5];
        for (int i = 0; i < field.length; i++ ) {
            for (int j = 0; j < field[i].length; j++ ) {
                field[i][j] = new Field();
                switch (i) {
                    case 0:
                        field[i][j].setType(Field.checker.white);
                        break;
                    case 1:
                        field[i][j].setType(Field.checker.black);
                        break;
                    default:
                        break;
                }

            }
        }
        Snapshot snapshot = new Snapshot(field, 'b', 5, 'w' );
        Assert.assertEquals(snapshot.toString(),"{\"status\":\"snapshot\",\"next\":\"w\",\"color\":\"b\",\"field\":[[\"white\", \"white\", \"white\", \"white\", \"white\"], [\"black\", \"black\", \"black\", \"black\", \"black\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"]],\"king\":[[\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"]]}");
    }

    @Test
    public void SnapshotTestWhite(){
        Field[][] field = new Field[5][5];
        for (int i = 0; i < field.length; i++ ) {
            for (int j = 0; j < field[i].length; j++ ) {
                field[i][j] = new Field();
                switch (i) {
                    case 0:
                        field[i][j].setType(Field.checker.white);
                        break;
                    case 1:
                        field[i][j].setType(Field.checker.black);
                        break;
                    default:
                        break;
                }

            }
        }
        Snapshot snapshot = new Snapshot(field, 'w', 5, 'b' );
        Assert.assertEquals(snapshot.toString(), "{\"status\":\"snapshot\",\"next\":\"b\",\"color\":\"w\",\"field\":[[\"white\", \"white\", \"white\", \"white\", \"white\"], [\"black\", \"black\", \"black\", \"black\", \"black\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"]],\"king\":[[\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\"]]}");
    }

    @Test
    public void SnapshotTestTestWhite(){
        Field[][] field = new Field[5][5];
        for (int i = 0; i < field.length; i++ ) {
            for (int j = 0; j < field[i].length; j++ ) {
                field[i][j] = new Field();
                switch (i) {
                    case 0:
                        field[i][j].setType(Field.checker.white);
                        break;
                    case 1:
                        field[i][j].setType(Field.checker.black);
                        break;
                    default:
                        break;
                }

            }
        }
        Snapshot snapshot = new Snapshot(field, 'w', 5, 'b' );
//        System.out.println(snapshot.toStringTest());
        Assert.assertEquals(snapshot.toStringTest(), "{'status':'snapshot','next':'b','color':'w','field':[['white', 'white', 'white', 'white', 'white'], ['black', 'black', 'black', 'black', 'black'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing']],'king':[['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false']]}");
    }


    @Test
    public void SnapshotTestTestBlack(){
        Field[][] field = new Field[5][5];
        for (int i = 0; i < field.length; i++ ) {
            for (int j = 0; j < field[i].length; j++ ) {
                field[i][j] = new Field();
                switch (i) {
                    case 0:
                        field[i][j].setType(Field.checker.white);
                        break;
                    case 1:
                        field[i][j].setType(Field.checker.black);
                        break;
                    default:
                        break;
                }

            }
        }
        Snapshot snapshot = new Snapshot(field, 'b', 5, 'w' );
        Assert.assertEquals(snapshot.toStringTest(), "{'status':'snapshot','next':'w','color':'b','field':[['white', 'white', 'white', 'white', 'white'], ['black', 'black', 'black', 'black', 'black'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing']],'king':[['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false']]}");
    }
}
