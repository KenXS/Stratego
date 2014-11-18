import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.*;
import java.text.*;
import javax.sound.sampled.*;

/****************************************************************************************/
/* Stratego - The standard Milton Bradley (?) board game implemented for compter play.  */
/*                                                                                      */
/* Here are the rules as originally stated for the game. This text is entirely          */
/* borrowed verbatim from web site:                                                     */
/*        www.inficad.com/~ecollins/stratego/stratego-rules-early.htm                   */
/*                                                                                      */
/* Stratego is the American version of the game now popular on the Continent.           */
/* While the pieces have military designs and are maneuvered across the playing board,  */
/* it is not a war game.                                                                */
/*                                                                                      */
/* Stratego is a fast moving game, which is easy to learn, delightful to play, and      */
/* which provides a never-ending variety of ways to outwit your opponent. The colorful  */
/* playing pieces are marked according to military rank which are kept hidden from the  */
/* opponent as they are placed and moved across the board to capture your opponent’s    */
/* "Flag". The rank of the piece is revealed only when an opposing piece is "struck" or */
/* attacked. The higher ranked piece removes the lesser rank. There are "Bombs" which   */
/* "blow-up" and remove any attacking piece except the "Miner" who can dismantle and    */
/* remove the "Bomb". Even the "Marshal", the highest ranking piece, has a weakness,    */
/* in that the lowly "Spy" can remove him from the game.                                */
/*                                                                                      */
/* The exciting elements of skillful planning, clever deception, memory and suspense    */
/* make Stratego a thoroughly delightful game.                                          */
/*                                                                                      */
/* Rules for Stratego                                                                   */
/*                                                                                      */
/* The object of the game is to capture the opponent’s flag.                            */
/*                                                                                      */
/* To start the game:                                                                   */
/*                                                                                      */
/* Place the board between the players so that the name Stratego is facing each         */
/* contestant.                                                                          */
/* One player takes the Red and the other the Blue playing pieces. Red starts first.    */
/* Each player gets an army of 40 pieces, in order of rank from high to low, consisting */
/* of:                                                                                  */
/* 1 Marshal                                                                            */
/* 1 General                                                                            */
/* 2 Colonels                                                                           */
/* 3 Majors                                                                             */
/* 4 Captains                                                                           */
/* 4 Lieutenants                                                                        */
/* 4 Sergeants                                                                          */
/* 5 Miners                                                                             */
/* 8 Scouts                                                                             */
/* 1 Spy                                                                                */
/*                                                                                      */
/* These are all movable pieces.                                                        */
/*                                                                                      */
/* There are also 6 Bombs and 1 Flag, which are not moveable. Note that the moveable    */
/* pieces have a number in the upper right corner to designate the order of rank.       */
/* Thus, the Marshal is ranked 1 (highest), the General 2, the Colonels 3, and so on to */
/* the Spy who is marked with an "S".                                                   */
/*                                                                                      */
/* The players place one piece in each square of their half of the board. All squares   */
/* are to be filled from each end. That is, 10 per row, 4 rows deep. The two middle     */
/* rows are to be left unoccupied at the start of the game.                             */ 
/* The pieces are placed with the notched ends up and the printed emblem facing the     */
/* player in such a way that the opponent does not know the arrangement of the pieces.  */
/* Read the rules of "Movement" and "Striking" so that an idea of how to plan the       */
/* placement of the pieces will be obtained.                                            */
/*                                                                                      */
/* Rules for Movement                                                                   */
/*                                                                                      */
/* Turns alternate, first Red then Blue.                                                */
/* A piece moves from square to square, one square at a time. (Exception: Scout –       */
/* see rule 8). A piece may be moved forward, backward, or sideward but not diagonally. */
/* Note that there are two lakes in the center of the board, which contain no squares.  */
/* Pieces must move around lakes and cannot move where there is no square.              */
/* Two pieces may not occupy the same square at the same time.                          */
/* A piece may not move through a square occupied by a piece nor jump over a piece.     */
/* Only one piece may be moved in each turn.                                            */
/* The "Flag" and the "Bomb" pieces cannot be moved. Once these pieces are placed at    */
/* the start of the game, they must remain in that square.                              */
/* The "Scout" may move any number of open squares forward, backward, or sideward in a  */
/* straight line if the player desires. This movement, of course, then reveals to the   */
/* opponent the value of that piece. Therefore, the player may choose to move the Scout */
/* only one square in his turn, so as to keep the Scout’s identity hidden. The Scout is */
/* valuable for probing the opponent’s positions. The Scout may not move and strike in  */
/* the same turn.                                                                       */
/*                                                                                      */
/* Once a piece has been moved to a square and the hand removed, it cannot be moved     */
/* back to its original position in that turn.                                          */
/* Pieces cannot be moved back and forth between the same 2 squares in 3 consecutive    */
/* turns.                                                                               */
/*                                                                                      */
/* A player must either "move" or "strike" in his turn.                                 */
/*                                                                                      */
/* Rules for "Strike" or Attack                                                         */
/*                                                                                      */
/* When a red and a blue piece occupy adjoining squares either back to back, side to    */
/* side, or face to face, they are in a position to attack or "strike". No diagonal     */
/* strikes can be made.                                                                 */
/* A player may move in his turn or strike in his turn. He cannot do both. The "strike" */
/* ends the turn. After pieces have finished the "strike" move, the player who was      */
/* struck has his turn to move or strike.                                               */
/* It is not required to "strike" when two opposing pieces are in position. A player    */
/* may decide to strike, whenever he desires.                                           */
/*                                                                                      */
/* Either player may strike (in his turn), not only the one who moves his piece into    */
/* position.                                                                            */
/* To strike (or attack), the player, whose turn it is, takes up his piece and lightly  */
/* "strikes" the opponent’s piece while at the same time declaring his piece’s rank.    */
/* The opponent answers by naming the rank of his piece.                                */
/*                                                                                      */
/* The piece with the lower rank is lost and removed from the board. The winning higher */
/* ranking piece is then moved immediately into the empty square formerly occupied by   */
/* the losing piece.                                                                    */
/*                                                                                      */
/* When equal ranks are struck, then both pieces are lost and removed from the board.   */
/* A Marshal removes a General, a General removes a Colonel, and a Colonel removes a    */
/* Major, and so on down to the Spy, which is the lowest ranking piece.                 */
/* The Spy, however, has the special privilege of being able to remove only the Marshal */
/* provided he strikes first. That is, if the Spy "strikes" the Marshal in his turn,    */
/* the Marshal is removed. However, if the Marshal "strikes" first, the Spy is removed. */
/* All other pieces remove the Spy regardless of who strikes first.                     */
/* When any piece (except a Miner) strikes a Bomb (Bang!) that piece is lost and is     */
/* removed from the board. The Bomb does not move into the empty square, but remains    */
/* in its original position at all times. When a Miner strikes a Bomb, the Bomb is lost */
/* and the miner moves into the unoccupied square.                                      */
/*                                                                                      */
/* A Bomb cannot strike, but rather must wait until a moveable piece strikes it.        */
/* Remember, the Flag also can never be moved.                                          */
/*                                                                                      */
/* To End the Game                                                                      */
/*                                                                                      */
/* Whenever a player "strikes" his opponent’s Flag, the game ends and he is the winner. */
/* If a player cannot move a piece or "strike" in his turn, he must give up and declare */
/* his opponent the winner.                                                             */
/*                                                                                      */
/* Some Suggestions For Strategy                                                        */
/*                                                                                      */
/* From the above it is clear that the original placement of the pieces can determine   */
/* the outcome. It is therefore good defensive tactics to surround the Flag with a few  */
/* Bombs. However, to mislead the opponent, it is recommended to place a few Bombs at   */
/* some distance from the Flag.                                                         */
/*                                                                                      */
/* A few high-ranking pieces in the front lines is a good play, but the player who      */
/* rapidly loses his high officers stands in a weak position.                           */
/*                                                                                      */
/* Scouts in the front line are useful to probe the strength of the opposing pieces.    */
/*                                                                                      */
/* Miners are very important near the end of the game so it is good strategy to place   */
/* some in the rear ranks                                                               */
/*                                                                                      */
/****************************************************************************************/
/* Important note - data structure boardSpot is 10x10 where x is up (north) down (south)*/
/* and y is left (west) right (east)                                                    */
/*                                                                                      */
/*                                                                                      */
/****************************************************************************************/
public class Stratego extends JFrame 
                      implements ChangeListener, 
                                 ItemListener, 
                                 ComponentListener, 
                                 MouseListener, 
                                 MouseMotionListener
{
    static final int COLMAX = 10;
    static final int ROWMAX = 10;

    static final int CNTEntities   = 12;

    static final int NBRMarshalls   = 1;
    static final int NBRGenerals    = 1;
    static final int NBRColonels    = 2;
    static final int NBRMajors      = 3;
    static final int NBRCaptains    = 4;
    static final int NBRLieutenants = 4;
    static final int NBRSeargants   = 4;
    static final int NBRMiners      = 5;
    static final int NBRScouts      = 8;
    static final int NBRSpys        = 1;
    static final int NBRBombs       = 6;
    static final int NBRFlags       = 1;

    static int nbrEntity[] = {NBRMarshalls, NBRGenerals, NBRColonels, NBRMajors, NBRCaptains, NBRLieutenants, 
                              NBRSeargants, NBRMiners,   NBRScouts,   NBRSpys,   NBRBombs,    NBRFlags};

    static final int IdxMarshall    = 0;
    static final int IdxGeneral     = 1;
    static final int IdxColonel     = 2;
    static final int IdxMajor       = 3;
    static final int IdxCaptain     = 4;
    static final int IdxLieutenant  = 5;
    static final int IdxSeargant    = 6;
    static final int IdxMiner       = 7;
    static final int IdxScout       = 8;
    static final int IdxSpy         = 9;
    static final int IdxBomb        = 10;
    static final int IdxFlag        = 11;

    static final int totalPieces = 40;
    static int totalAssigned = 0;
   
    static int moves = 0;

    static int opponentStrategy = -1;
    static int pieceSelected = -1;

    static boolean exposeOpponent = false;

    static boolean manualRedSetup = true;

    // these tables hold counts of active soldiers (not dead/captured): red/blu & soldier rank
    int[][] teamSoldier = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                           {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    Color water = Color.cyan;

    static final int NoOne   = -1;

    static final int TieTeam = -2;
    static final int NoTeam  = -1;
    static final int RedTeam = 0;
    static final int BluTeam = 1;

    // These variables serve image painting after attack is concluded 
    static Image winningPic, losingPic1, losingPic2 = null;
    static int winner = NoTeam;
    static int losingRedX, losingRedY, losingBluX, losingBluY, winningX, winningY = 0;
    static int losingWhoBlu, losingWhoRed = NoOne;

    static Image bluRight = Toolkit.getDefaultToolkit().getImage("_BlueRight.gif");
    static Image bluLeft  = Toolkit.getDefaultToolkit().getImage("_BlueLeft.gif");
    static Image bluUp    = Toolkit.getDefaultToolkit().getImage("_BlueUp.gif");
    static Image bluDown  = Toolkit.getDefaultToolkit().getImage("_BlueDown.gif");
    //static Image bluNone  = Toolkit.getDefaultToolkit().getImage("_BlueNone.gif");
    boardSpot trailSpot = null;

    static long delayMillis = 10000;
    static int  chgVapor = 5;

    // these colors correspond to the colors associated with the two teams:
    Color[] teamColor = {Color.red, Color.blue};

    // Both teams are represented by 40 pieces of varying character 
    piece[][] team = new piece[2][totalPieces];
    piece[]   pieceSet = new piece[2 * totalPieces];

    // Control Buttons under Title
    JButton jbExpose  = new JButton("Expose Blue");
    JButton jbResetB  = new JButton("Reset Blue");
    JButton jbResetR  = new JButton("Reset Red");
    JButton jbResetAll= new JButton("Reset All");
    JButton jbStart   = new JButton("Start Game");

    JLabel lOppStr = new JLabel("");
    JLabel lMoves  = new JLabel("");
    JLabel lTurn   = new JLabel("Setup Mode");

    File soundExplode = new File("Explode.wav");
    Clip clipExplode;
    File soundPieceAdd = new File("Findlist.wav");
    Clip clipPieceAdd;
    File soundPieceRmv = new File("Whoosh.wav");
    Clip clipPieceRmv;
    File soundPieceErr = new File("Beep.wav");
    Clip clipPieceErr;
    File soundWin = new File("Clap.wav");
    Clip clipWin;
    File soundCaptured = new File("Sndspcll.wav");
    Clip clipCaptured;
    File soundCapture = new File("Ricochet.wav");
    Clip clipCapture;
    File soundMoved = new File("Btnup.wav");
    Clip clipMoved;
    File soundBluMoved = new File("Btndown.wav");
    Clip clipBluMoved;
    File soundDefused = new File("Projctor.wav");
    Clip clipDefused;
    File soundAttrition = new File("punch123[1].wav");
    Clip clipAttrition;

    // Team Piece Buttons
    jbuttonC bRedTitle01 = new jbuttonC(Color.red);
    jbuttonC bRedTitle02 = new jbuttonC(Color.red);
    jbuttonC bRedTitle03 = new jbuttonC(Color.red);
    jbuttonC bRedTitle04 = new jbuttonC(Color.red);
    jbuttonC bRedTitle05 = new jbuttonC(Color.red);
    jbuttonC bRedTitle06 = new jbuttonC(Color.red);
    jbuttonC bRedTitle07 = new jbuttonC(Color.red);
    jbuttonC bRedTitle08 = new jbuttonC(Color.red);
    jbuttonC bRedTitle09 = new jbuttonC(Color.red);
    jbuttonC bRedTitle10 = new jbuttonC(Color.red);
    jbuttonC bRedTitle11 = new jbuttonC(Color.red);
    jbuttonC bRedTitle12 = new jbuttonC(Color.red);

    jbuttonC bBluTitle01 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle02 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle03 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle04 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle05 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle06 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle07 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle08 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle09 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle10 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle11 = new jbuttonC(Color.blue);
    jbuttonC bBluTitle12 = new jbuttonC(Color.blue);

    Strategy strategy = new Strategy();
 
    static boardSpot bsDraggedFrom = null;
    static boardSpot bsDraggedTo = null;

    static boardSpot[][] board = new boardSpot[10][10];

    static final String strDesc[] = {"Marshall","General",   "Colonel", "Major",
                                     "Captain", "Lieutenant","Seargant","Miner",
                                     "Scout",   "Spy",       "Bomb",    "Flag"};

    static int valEntity[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 99, 0};

    // 0=not yet started, game pieces not yet assigned to start location
    // 1=not yet started, game pieces assigned partially to gameboard
    // 2=not yet started, game pieces assigned fully to gameboard, but game not yet started 
    // 3=running        , game pieces scrambled on the board, with game in progress
    // 4=complete       , game pieces scrambled on the board, with a winner decided 
    int animationStatus = 0;
    static final int NewGameEmptyBoard   = 0;
    static final int NewGamePartialBoard = 1;
    static final int NewGameFullBoard    = 2;
    static final int RunningGame         = 3;
    static final int RunningGameRedTurn  = 3;
    static final int RunningGameBluTurn  = 4;
    static final int CompleteGame        = 5;
    static final int CompleteGameRedWins = 6;
    static final int CompleteGameBluWins = 7;
    static final int CompleteGameDraw    = 8;
    static boolean drawing = false; 
    //static boolean piecesMoved = false; 

    Dimension majorPanelSize, panelLeftSize, panelRightSize;

    static int controlH, titleH; 
    static int highestLiveRedRank = 10;
    static int highestLiveBluRank = 10;

    //String fontname = "Courier";
    //int type = Font.PLAIN;
    //int size = 36;
    //Font font;

    // The parameter is a map of the Red team's start positions. Valid contents is optional. Otherwise
    // all values are -1. t some time in the future, this can be enhanced to also specify the Blue 
    // team's start position and these may not necessarily be beginning of the game positions. They
    // could be middle game positions, for resuming a game started andsaved at an earlier time.
    public Stratego(int[] RedMap) 
    {
        super("Stratego");

        addComponentListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        //type = type | Font.BOLD;
        //font = new Font(fontname, type, size);
        //setFont(font);

        // instantiate 40 game pieces, for both teams, and assign each to the multidimensional team array.
        int z = 0;
        for (int tm=0; tm<2; tm++) { 
             int t = 0;
             for (int i = 0; i < NBRMarshalls;  i++) {
                  team[tm][t] = new piece(t, tm, IdxMarshall  , valEntity[IdxMarshall  ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRGenerals;   i++) {
                  team[tm][t] = new piece(t, tm, IdxGeneral   , valEntity[IdxGeneral   ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRColonels;   i++) {
                  team[tm][t] = new piece(t, tm, IdxColonel   , valEntity[IdxColonel   ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRMajors;     i++) {
                  team[tm][t] = new piece(t, tm, IdxMajor     , valEntity[IdxMajor     ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRCaptains;   i++) {
                  team[tm][t] = new piece(t, tm, IdxCaptain   , valEntity[IdxCaptain   ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRLieutenants;i++) {
                  team[tm][t] = new piece(t, tm, IdxLieutenant, valEntity[IdxLieutenant]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRSeargants;  i++) {
                  team[tm][t] = new piece(t, tm, IdxSeargant  , valEntity[IdxSeargant])  ; pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRMiners;     i++) {
                  team[tm][t] = new piece(t, tm, IdxMiner     , valEntity[IdxMiner     ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRScouts;     i++) { 
                  team[tm][t] = new piece(t, tm, IdxScout     , valEntity[IdxScout     ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRSpys;       i++) {
                  team[tm][t] = new piece(t, tm, IdxSpy       , valEntity[IdxSpy       ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRBombs;      i++) {
                  team[tm][t] = new piece(t, tm, IdxBomb      , valEntity[IdxBomb      ]); pieceSet[z] = team[tm][t]; t++; z++;}
             for (int i = 0; i < NBRFlags;      i++) {
                  team[tm][t] = new piece(t, tm, IdxFlag      , valEntity[IdxFlag      ]); pieceSet[z] = team[tm][t]; t++; z++;}
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Create 10x10 row/col board spot objects.... 
        for (int bsX=0; bsX < ROWMAX; bsX++) {
             for (int bsY=0; bsY < COLMAX; bsY++) {
                  board[bsX][bsY] = new boardSpot(0,0,0,0,bsX,bsY, true, false, null);
             }
        }
        // Assign terrain attribute...perhaps in the future these could be configurable to provide an interesting
        // feature
        board[4][2].setLand(false);
        board[4][3].setLand(false);
        board[4][6].setLand(false);
        board[4][7].setLand(false);
        board[5][2].setLand(false);
        board[5][3].setLand(false);
        board[5][6].setLand(false);
        board[5][7].setLand(false);

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Assign valid start positions for red & blue
        for (int x = 0; x <= 3; x++) 
             for(int y = 0; y <= 9; y++)
                 board[x][y].setStartSlot(BluTeam); 
        for (int x = 6; x <= 9; x++)
             for(int y = 0; y <= 9; y++)
                 board[x][y].setStartSlot(RedTeam); 
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // Map the program parameter start positions into the data structures of the game
        // The data was already validate for correctness in the before being passes into 
        // this class. THe array is comprised of all -1 values, if no start positions are specified
        if (RedMap[0] >= 0) {
            manualRedSetup = false;
            boolean done = false;
            int x,y,t = 0;

            for (int r=0; r <= 39; r++) {
                 done = false;
                 x = r / 10 + 6;     // row
                 switch(x) {
                   case 6: x = 9; break;
                   case 7: x = 8; break;
                   case 8: x = 7; break;
                   case 9: x = 6; break;
                 } 
                 y = r % 10;         // col
                 t = 0;
                 while (!done && t < 40) {
                         if (!team[RedTeam][t].isAssignedToBoard() && 
                              team[RedTeam][t].getWho() == RedMap[r]) {
                              //System.out.println("x.y=" + x + "." + y + "...");
                              board[x][y].setPiece(team[RedTeam][t]);
                              board[x][y].setOccupied(true);
                              team[RedTeam][t].setAssignedToBoard(true);
                              done = true;
                          }
                          else t++;
                 }
                 if (t >= 40) { 
                     System.out.println("Cannot load start position into board!");
                     System.exit(0);
                 }
             }
             totalAssigned = totalPieces;
             bRedTitle01.setEnabled(false);
             bRedTitle02.setEnabled(false);
             bRedTitle03.setEnabled(false);
             bRedTitle04.setEnabled(false);
             bRedTitle05.setEnabled(false);
             bRedTitle06.setEnabled(false);
             bRedTitle07.setEnabled(false);
             bRedTitle08.setEnabled(false);
             bRedTitle09.setEnabled(false);
             bRedTitle10.setEnabled(false);
             bRedTitle11.setEnabled(false);
             bRedTitle12.setEnabled(false);
        }
 
        // At start of game, each team has a full complement of all soldiers - red team
        if (manualRedSetup) {
            teamSoldier[RedTeam][IdxMarshall  ] = NBRMarshalls;
            teamSoldier[RedTeam][IdxGeneral   ] = NBRGenerals;
            teamSoldier[RedTeam][IdxColonel   ] = NBRColonels;
            teamSoldier[RedTeam][IdxMajor     ] = NBRMajors;
            teamSoldier[RedTeam][IdxCaptain   ] = NBRCaptains;
            teamSoldier[RedTeam][IdxLieutenant] = NBRLieutenants;
            teamSoldier[RedTeam][IdxSeargant  ] = NBRSeargants;
            teamSoldier[RedTeam][IdxMiner     ] = NBRMiners;
            teamSoldier[RedTeam][IdxScout     ] = NBRScouts;
            teamSoldier[RedTeam][IdxSpy       ] = NBRSpys;
            teamSoldier[RedTeam][IdxBomb      ] = NBRBombs;
            teamSoldier[RedTeam][IdxFlag      ] = NBRFlags;
        }

        // At start of game, each team has a full complement of all soldiers - red team
        teamSoldier[BluTeam][IdxMarshall  ] = NBRMarshalls;
        teamSoldier[BluTeam][IdxGeneral   ] = NBRGenerals;
        teamSoldier[BluTeam][IdxColonel   ] = NBRColonels;
        teamSoldier[BluTeam][IdxMajor     ] = NBRMajors;
        teamSoldier[BluTeam][IdxCaptain   ] = NBRCaptains;
        teamSoldier[BluTeam][IdxLieutenant] = NBRLieutenants;
        teamSoldier[BluTeam][IdxSeargant  ] = NBRSeargants;
        teamSoldier[BluTeam][IdxMiner     ] = NBRMiners;
        teamSoldier[BluTeam][IdxScout     ] = NBRScouts;
        teamSoldier[BluTeam][IdxSpy       ] = NBRSpys;
        teamSoldier[BluTeam][IdxBomb      ] = NBRBombs;
        teamSoldier[BluTeam][IdxFlag      ] = NBRFlags;

        bRedTitle01.setText(teamSoldier[RedTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
        bRedTitle01.setHorizontalAlignment(bRedTitle01.LEFT);
        bRedTitle02.setText(teamSoldier[RedTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
        bRedTitle02.setHorizontalAlignment(bRedTitle02.LEFT);
        bRedTitle03.setText(teamSoldier[RedTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
        bRedTitle03.setHorizontalAlignment(bRedTitle03.LEFT);
        bRedTitle04.setText(teamSoldier[RedTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
        bRedTitle04.setHorizontalAlignment(bRedTitle04.LEFT);
        bRedTitle05.setText(teamSoldier[RedTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
        bRedTitle05.setHorizontalAlignment(bRedTitle05.LEFT);
        bRedTitle06.setText(teamSoldier[RedTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
        bRedTitle06.setHorizontalAlignment(bRedTitle06.LEFT);
        bRedTitle07.setText(teamSoldier[RedTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
        bRedTitle07.setHorizontalAlignment(bRedTitle07.LEFT);
        bRedTitle08.setText(teamSoldier[RedTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
        bRedTitle08.setHorizontalAlignment(bRedTitle08.LEFT);
        bRedTitle09.setText(teamSoldier[RedTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
        bRedTitle09.setHorizontalAlignment(bRedTitle09.LEFT);
        bRedTitle10.setText(teamSoldier[RedTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
        bRedTitle10.setHorizontalAlignment(bRedTitle10.LEFT);
        bRedTitle11.setText(teamSoldier[RedTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
        bRedTitle11.setHorizontalAlignment(bRedTitle11.LEFT);
        bRedTitle12.setText(teamSoldier[RedTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);
        bRedTitle12.setHorizontalAlignment(bRedTitle12.LEFT);

        bBluTitle01.setText(teamSoldier[BluTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
        bBluTitle01.setHorizontalAlignment(bBluTitle01.LEFT);
        bBluTitle02.setText(teamSoldier[BluTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
        bBluTitle02.setHorizontalAlignment(bBluTitle02.LEFT);
        bBluTitle03.setText(teamSoldier[BluTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
        bBluTitle03.setHorizontalAlignment(bBluTitle03.LEFT);
        bBluTitle04.setText(teamSoldier[BluTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
        bBluTitle04.setHorizontalAlignment(bBluTitle04.LEFT);
        bBluTitle05.setText(teamSoldier[BluTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
        bBluTitle05.setHorizontalAlignment(bBluTitle05.LEFT);
        bBluTitle06.setText(teamSoldier[BluTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
        bBluTitle06.setHorizontalAlignment(bBluTitle06.LEFT);
        bBluTitle07.setText(teamSoldier[BluTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
        bBluTitle07.setHorizontalAlignment(bBluTitle07.LEFT);
        bBluTitle08.setText(teamSoldier[BluTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
        bBluTitle08.setHorizontalAlignment(bBluTitle08.LEFT);
        bBluTitle09.setText(teamSoldier[BluTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
        bBluTitle09.setHorizontalAlignment(bBluTitle09.LEFT);
        bBluTitle10.setText(teamSoldier[BluTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
        bBluTitle10.setHorizontalAlignment(bBluTitle10.LEFT);
        bBluTitle11.setText(teamSoldier[BluTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
        bBluTitle11.setHorizontalAlignment(bBluTitle11.LEFT);
        bBluTitle12.setText(teamSoldier[BluTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);
        bBluTitle12.setHorizontalAlignment(bBluTitle12.LEFT);

        //JLabel labelStepsRed = new JLabel("Moves: 0");
        //JLabel labelStepsBlu = new JLabel("Moves: 0");
 
        jpanel jPanelNorth   = new jpanel(Color.lightGray);   // Banner components go here

        jpanel jPanelLeft    = new jpanel(Color.lightGray);   // Red Team summary goes here
        jPanelLeft.setBorder(BorderFactory.createLineBorder(Color.black));
        jpanel jPanelCenter  = new jpanel(Color.lightGray);   // Game board goes here
        jPanelCenter.setBorder(BorderFactory.createLineBorder(Color.cyan));
        jpanel jPanelRight   = new jpanel(Color.lightGray);   // Blue Team summary goes here
        jPanelRight.setBorder(BorderFactory.createLineBorder(Color.black));

        jPanelNorth.setLayout(new BoxLayout(jPanelNorth, BoxLayout.Y_AXIS));
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelCenter.setLayout(new BoxLayout(jPanelCenter, BoxLayout.Y_AXIS));
        jPanelRight.setLayout(new BoxLayout(jPanelRight, BoxLayout.Y_AXIS));

        jStrPanel jPanelRed = new jStrPanel(teamColor[RedTeam]);  // vertical tray of red & blue peices
        jStrPanel jPanelBlu = new jStrPanel(teamColor[BluTeam]);

        jpanel jPanelTitle   = new jpanel(Color.lightGray);   // Title Letters go here
        jPanelTitle.setLayout(new BoxLayout(jPanelTitle, BoxLayout.X_AXIS));
        titleH = jPanelTitle.getHeight();

        jpanel jPanelControls = new jpanel(Color.lightGray);   // Control Buttons go here
        jPanelControls.setLayout(new FlowLayout(FlowLayout.LEFT));
        controlH = jPanelControls.getHeight();

        jPanelRed.add(bRedTitle01);
        jPanelRed.add(bRedTitle02);
        jPanelRed.add(bRedTitle03);
        jPanelRed.add(bRedTitle04);
        jPanelRed.add(bRedTitle05);
        jPanelRed.add(bRedTitle06);
        jPanelRed.add(bRedTitle07);
        jPanelRed.add(bRedTitle08);
        jPanelRed.add(bRedTitle09);
        jPanelRed.add(bRedTitle10);
        jPanelRed.add(bRedTitle11);
        jPanelRed.add(bRedTitle12);

        jPanelBlu.add(bBluTitle01);
        jPanelBlu.add(bBluTitle02);
        jPanelBlu.add(bBluTitle03);
        jPanelBlu.add(bBluTitle04);
        jPanelBlu.add(bBluTitle05);
        jPanelBlu.add(bBluTitle06);
        jPanelBlu.add(bBluTitle07);
        jPanelBlu.add(bBluTitle08);
        jPanelBlu.add(bBluTitle09);
        jPanelBlu.add(bBluTitle10);
        jPanelBlu.add(bBluTitle11);
        jPanelBlu.add(bBluTitle12);

        jPanelLeft.add(jPanelRed); 
        jPanelRight.add(jPanelBlu);

        jPanelTitle.add(Box.createHorizontalStrut(10));
        JLabel bTitle1 = new JLabel("", new ImageIcon("_TitleS.gif"), JLabel.CENTER);
        JLabel bTitle2 = new JLabel("", new ImageIcon("_TitleT2.gif"), JLabel.CENTER);
        JLabel bTitle3 = new JLabel("", new ImageIcon("_TitleR.gif"), JLabel.CENTER);
        JLabel bTitle4 = new JLabel("", new ImageIcon("_TitleA.gif"), JLabel.CENTER);
        JLabel bTitle5 = new JLabel("", new ImageIcon("_TitleT.gif"), JLabel.CENTER);
        JLabel bTitle6 = new JLabel("", new ImageIcon("_TitleE.gif"), JLabel.CENTER);
        JLabel bTitle7 = new JLabel("", new ImageIcon("_TitleG.gif"), JLabel.CENTER);
        JLabel bTitle8 = new JLabel("", new ImageIcon("_TitleO.gif"), JLabel.CENTER);
        jPanelTitle.add(bTitle1); 
        jPanelTitle.add(Box.createGlue());
        jPanelTitle.add(bTitle2); 
        jPanelTitle.add(Box.createGlue());
        jPanelTitle.add(bTitle3); 
        jPanelTitle.add(Box.createGlue());
        jPanelTitle.add(bTitle4); 
        jPanelTitle.add(Box.createGlue());
        jPanelTitle.add(bTitle5); 
        jPanelTitle.add(Box.createGlue());
        jPanelTitle.add(bTitle6); 
        jPanelTitle.add(Box.createGlue());
        jPanelTitle.add(bTitle7); 
        jPanelTitle.add(Box.createGlue());
        jPanelTitle.add(bTitle8); 
        jPanelNorth.add(jPanelTitle);

        titleH = bTitle1.getHeight();

        jPanelControls.add(jbStart); 
        jPanelControls.add(jbResetAll); 
        jPanelControls.add(jbResetR); 
        jPanelControls.add(jbResetB); 
        jPanelControls.add(jbExpose); 
        jPanelControls.add(lOppStr); 
        jPanelControls.add(lMoves); 
        jPanelControls.add(lTurn); 
        jPanelNorth.add(jPanelControls);

         if (manualRedSetup)
             jbStart.setEnabled(false);
         else
             jbStart.setEnabled(true);

        // Instantiate major panel as BorderLayout: west,north,east,south,center
        jMajPanel jPanelMajor = new jMajPanel(jPanelLeft,jPanelNorth,jPanelRight,null,jPanelCenter);
        contentPane.add(jPanelMajor);

        // set up the 100x100 animation grid...
        AnimationPanel p = new AnimationPanel();  

        jPanelCenter.add(p);
             Point pt = null;
             //pt = jPanelCenter.p.getLocation();
             //System.out.println("POINT--->" + pt.x + "." + pt.y);

        ResetOpponent();

        // start the background thread here...
        AnimationThread athread = new AnimationThread(); 

        // Only active during initial game setup - left click the button to select a piece, then
        // left click a boardSpot to place the selected rank in that spot. Right click on the 
        // placed piece to return the piece to the tray.
        bRedTitle01.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle01.setSelected(true);
                    pieceSelected = IdxMarshall;
             } // end ActionPerformed 
        });  // end of bRedTitle01

        bRedTitle02.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle02.setSelected(true);
                    pieceSelected = IdxGeneral;
             } // end ActionPerformed 
        });  // end of bRedTitle02

        bRedTitle03.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle03.setSelected(true);
                    pieceSelected = IdxColonel;
             } // end ActionPerformed 
        });  // end of bRedTitle03

        bRedTitle04.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle04.setSelected(true);
                    pieceSelected = IdxMajor;
             } // end ActionPerformed 
        });  // end of bRedTitle04

        bRedTitle05.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle05.setSelected(true);
                    pieceSelected = IdxCaptain;
             } // end ActionPerformed 
        });  // end of bRedTitle05

        bRedTitle06.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle06.setSelected(true);
                    pieceSelected = IdxLieutenant;
             } // end ActionPerformed 
        });  // end of bRedTitle06

        bRedTitle07.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle07.setSelected(true);
                    pieceSelected = IdxSeargant;
             } // end ActionPerformed 
        });  // end of bRedTitle07

        bRedTitle08.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle08.setSelected(true);
                    pieceSelected = IdxMiner;
             } // end ActionPerformed 
        });  // end of bRedTitle08

        bRedTitle09.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle09.setSelected(true);
                    pieceSelected = IdxScout;
             } // end ActionPerformed 
        });  // end of bRedTitle09

        bRedTitle10.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle10.setSelected(true);
                    pieceSelected = IdxSpy;
             } // end ActionPerformed 
        });  // end of bRedTitle10

        bRedTitle11.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle11.setSelected(true);
                    pieceSelected = IdxBomb;
             } // end ActionPerformed 
        });  // end of bRedTitle11

        bRedTitle12.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                    unselectAll();
                    bRedTitle12.setSelected(true);
                    pieceSelected = IdxFlag;
             } // end ActionPerformed 
        });  // end of bRedTitle01

        // The Start button is pressed...this procedure is performed. When all peices are placed
        // their startup postions, the Start button is enabled/activated.

        jbStart.addActionListener(new ActionListener()
        {    public void actionPerformed(ActionEvent event) {
                 jbResetR.setEnabled(false);
                 jbResetB.setEnabled(false);
                 //jbExpose.setEnabled(false); Dont allow exposing of Blue after game starts?
                 ResetRed(); 
                 animationStatus = RunningGameRedTurn;
                 delayMillis = 2000;
             } // end ActionPerformed 
        });  // end of jbStart

        jbExpose.addActionListener(new ActionListener()
        {
             public void actionPerformed(ActionEvent event) {
                  if (exposeOpponent) {           // start hiding now
                     jbExpose.setText("Expose Blue");
                     exposeOpponent = false;
                     lOppStr.setText("");
                  }
                  else {                          // start exposing now
                     jbExpose.setText("Hide Blue");
                     exposeOpponent = true;
                     lOppStr.setText(strategy.toString());
                  }
                  jbExpose.setEnabled(true);
                  repaint();
             } // end ActionPerformed 
        });  // end of jbExpose

        jbResetB.addActionListener(new ActionListener()
        {
             public void actionPerformed(ActionEvent event) {
                 //System.out.println("Reseting Opponent----------------------------------");
                 lTurn.setText("Setup Mode");
                 lMoves.setText("");
                 lOppStr.setText("");
                 if (animationStatus < RunningGame) {
                     for (int x = 0; x < ROWMAX; x++)
                          for (int y = 0; y < COLMAX; y++) 
                               if (board[x][y].getTeam() == BluTeam) { board[x][y].init("reset"); }
                     for (int t = 0; t < 40; t++)
                          team[BluTeam][t].init();
                     ResetOpponent();
                     //lOppStr.setText(strategy.toString());
                     repaint();
                 }
                 animationStatus = NewGameEmptyBoard;
             } // end ActionPerformed 
        });  // end of jbResetB

        jbResetR.addActionListener(new ActionListener()
        {
             public void actionPerformed(ActionEvent event) {
                 lTurn.setText("Setup Mode");
                 lMoves.setText("");
                 lOppStr.setText("");
                 if (animationStatus < RunningGame) {
                     jbStart.setEnabled(false);
                     for (int x = 0; x < ROWMAX; x++)
                          for (int y = 0; y < COLMAX; y++)
                               if (board[x][y].getTeam() == RedTeam) board[x][y].init("reset");
                     for (int t = 0; t < 40; t++)
                          team[RedTeam][t].init();

                     totalAssigned = 0;
                     ResetRed(); 
                     repaint();
                 }
                 animationStatus = NewGameEmptyBoard;
             } // end ActionPerformed 
        });  // end of jbResetR

        jbResetAll.addActionListener(new ActionListener()
        {
             public void actionPerformed(ActionEvent event) {
                 lTurn.setText("Setup Mode");
                 lMoves.setText("");
                 lOppStr.setText("");
                 if (animationStatus < CompleteGame) {
                     for (int x = 0; x < ROWMAX; x++)
                          for (int y = 0; y < COLMAX; y++)
                               board[x][y].init("reset");
                     for (int tm=0; tm<2; tm++)
                          for (int t = 0; t < 40; t++)
                              team[tm][t].init();
                     totalAssigned = 0;
                     ResetRed(); 
                     ResetBluTrayText();
                     ResetOpponent();
                     //lOppStr.setText(strategy.toString());
                     repaint();
                 }
                 animationStatus = NewGameEmptyBoard;
             } // end ActionPerformed 
        });  // end of jbResetAll

    } // end of Stratego constructor

      private void ResetRed() {
           teamSoldier[RedTeam][IdxMarshall  ] = NBRMarshalls;
           teamSoldier[RedTeam][IdxGeneral   ] = NBRGenerals;
           teamSoldier[RedTeam][IdxColonel   ] = NBRColonels;
           teamSoldier[RedTeam][IdxMajor     ] = NBRMajors;
           teamSoldier[RedTeam][IdxCaptain   ] = NBRCaptains;
           teamSoldier[RedTeam][IdxLieutenant] = NBRLieutenants;
           teamSoldier[RedTeam][IdxSeargant  ] = NBRSeargants;
           teamSoldier[RedTeam][IdxMiner     ] = NBRMiners;
           teamSoldier[RedTeam][IdxScout     ] = NBRScouts;
           teamSoldier[RedTeam][IdxSpy       ] = NBRSpys;
           teamSoldier[RedTeam][IdxBomb      ] = NBRBombs;
           teamSoldier[RedTeam][IdxFlag      ] = NBRFlags;

           bRedTitle01.setText(teamSoldier[RedTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
           bRedTitle02.setText(teamSoldier[RedTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
           bRedTitle03.setText(teamSoldier[RedTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
           bRedTitle04.setText(teamSoldier[RedTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
           bRedTitle05.setText(teamSoldier[RedTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
           bRedTitle06.setText(teamSoldier[RedTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
           bRedTitle07.setText(teamSoldier[RedTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
           bRedTitle08.setText(teamSoldier[RedTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
           bRedTitle09.setText(teamSoldier[RedTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
           bRedTitle10.setText(teamSoldier[RedTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
           bRedTitle11.setText(teamSoldier[RedTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
           bRedTitle12.setText(teamSoldier[RedTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);

           bRedTitle01.setEnabled(true);
           bRedTitle02.setEnabled(true);
           bRedTitle03.setEnabled(true);
           bRedTitle04.setEnabled(true);
           bRedTitle05.setEnabled(true);
           bRedTitle06.setEnabled(true);
           bRedTitle07.setEnabled(true);
           bRedTitle08.setEnabled(true);
           bRedTitle09.setEnabled(true);
           bRedTitle10.setEnabled(true);
           bRedTitle11.setEnabled(true);
           bRedTitle12.setEnabled(true);
      } // end of ResetRed

      private void ResetRedTrayText() {
           bRedTitle01.setText(teamSoldier[RedTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
           bRedTitle02.setText(teamSoldier[RedTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
           bRedTitle03.setText(teamSoldier[RedTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
           bRedTitle04.setText(teamSoldier[RedTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
           bRedTitle05.setText(teamSoldier[RedTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
           bRedTitle06.setText(teamSoldier[RedTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
           bRedTitle07.setText(teamSoldier[RedTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
           bRedTitle08.setText(teamSoldier[RedTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
           bRedTitle09.setText(teamSoldier[RedTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
           bRedTitle10.setText(teamSoldier[RedTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
           bRedTitle11.setText(teamSoldier[RedTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
           bRedTitle12.setText(teamSoldier[RedTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);
      }

      private void ResetBluTrayText() {
           bBluTitle01.setText(teamSoldier[BluTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
           bBluTitle02.setText(teamSoldier[BluTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
           bBluTitle03.setText(teamSoldier[BluTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
           bBluTitle04.setText(teamSoldier[BluTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
           bBluTitle05.setText(teamSoldier[BluTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
           bBluTitle06.setText(teamSoldier[BluTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
           bBluTitle07.setText(teamSoldier[BluTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
           bBluTitle08.setText(teamSoldier[BluTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
           bBluTitle09.setText(teamSoldier[BluTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
           bBluTitle10.setText(teamSoldier[BluTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
           bBluTitle11.setText(teamSoldier[BluTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
           bBluTitle12.setText(teamSoldier[BluTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);
      }

      private void ResetOpponent() {
        int[] setup = new int[40];

        setup = strategy.reset();

        int x = 0;
        int y = 0;
        int t = 0;
        boolean done = false;
        for (int b=39; b>=0; b--) {
             x = b / 10; // row
             y = b % 10; // col
             //System.out.print("ResetOpponent-1: assigning piece to " + x + "." + y + "...");
             if (board[x][y].isLand()) {
                 // find next unused game piece object matching indentity preset in table  
                 t = 0;
                 done = false;
                 //System.out.print("Reset-0: <" + strategy.toString() + ">" + setup[b]);
                 while (!done && t < 40) {

                        if (!team[BluTeam][t].isAssignedToBoard() && 
                             team[BluTeam][t].getWho() == setup[b]) {
                             board[x][y].setPiece(team[BluTeam][t]);
                             board[x][y].setOccupied(true);
                             team[BluTeam][t].setAssignedToBoard(true);
                             done = true;
                        }
                        else t++;
                 }
                 if (!done){
                     System.out.println("ResetOpponent: error#1: cant find matching piece is set");
                     System.exit(0);
                 } 
             }
             //System.out.println("=>" + board[x][y].toString());
        }
    } // end of ResetOpponent

    private void trayUpdate(int c, int uTeam, int uWho) {
         int i = 0;
         //System.out.println("trayUpdate-0: Team=" + uTeam);
         //System.out.println("trayUpdate-1: Who =" + uWho);
         ResetRedTrayText();
         if (uWho == NoOne) return; 
 
         if (uTeam == RedTeam) {
            ResetRedTrayText();
            teamSoldier[uTeam][uWho] += c;
            switch(uWho) {
              case IdxMarshall:  
                   if (teamSoldier[RedTeam][IdxMarshall] <= 0) {
                       bRedTitle01.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle01.setText("->" + teamSoldier[RedTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
                   return;
              case IdxGeneral:  
                   if (teamSoldier[RedTeam][IdxGeneral ] <= 0) {
                       bRedTitle02.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle02.setText("->" + teamSoldier[RedTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
                   return;
              case IdxColonel:  
                   if (teamSoldier[RedTeam][IdxColonel ] <= 0) {
                       bRedTitle03.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle03.setText("->" + teamSoldier[RedTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
                   return;
              case IdxMajor:  
                   if (teamSoldier[RedTeam][IdxMajor     ] <= 0) {
                       bRedTitle04.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle04.setText("->" + teamSoldier[RedTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
                   return;
              case IdxCaptain:  
                   if (teamSoldier[RedTeam][IdxCaptain   ] <= 0) {
                       bRedTitle05.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle05.setText("->" + teamSoldier[RedTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
                   return;
              case IdxLieutenant:  
                   if (teamSoldier[RedTeam][IdxLieutenant] <= 0) {
                       bRedTitle06.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle06.setText("->" + teamSoldier[RedTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
                   return;
              case IdxSeargant:  
                   if (teamSoldier[RedTeam][IdxSeargant] <= 0) {
                       bRedTitle07.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle07.setText("->" + teamSoldier[RedTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
                   return;
              case IdxMiner:  
                   if (teamSoldier[RedTeam][IdxMiner   ] <= 0) {
                       bRedTitle08.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle08.setText("->" + teamSoldier[RedTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
                   return;
              case IdxScout:  
                   if (teamSoldier[RedTeam][IdxScout   ] <= 0) {
                       bRedTitle09.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle09.setText("->" + teamSoldier[RedTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
                   return;
              case IdxSpy:  
                   if (teamSoldier[RedTeam][IdxSpy     ] <= 0) {
                       bRedTitle10.setEnabled(false);
                       while (teamSoldier[RedTeam][i] == 0) {highestLiveRedRank = valEntity[i++];}
                   }
                   bRedTitle10.setText("->" + teamSoldier[RedTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
                   return;
              case IdxBomb:  
                   if (teamSoldier[RedTeam][IdxBomb    ] <= 0) {
                       bRedTitle11.setEnabled(false);
                   }
                   bRedTitle11.setText("->" + teamSoldier[RedTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
                   return;
              case IdxFlag:  
                   if (teamSoldier[RedTeam][IdxFlag    ] <= 0) {
                       bRedTitle12.setEnabled(false);
                   }
                   bRedTitle12.setText("->" + teamSoldier[RedTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);
                   return;
            }
         }
         if (uTeam == BluTeam) {
            ResetBluTrayText();
            teamSoldier[uTeam][uWho] += c;
            switch(uWho) {
              case IdxMarshall:  
                   if (teamSoldier[BluTeam][IdxMarshall] <= 0) {
                       bBluTitle01.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle01.setText("->" + teamSoldier[BluTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
                   return;
              case IdxGeneral:  
                   if (teamSoldier[BluTeam][IdxGeneral ] <= 0) {
                       bBluTitle02.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle02.setText("->" + teamSoldier[BluTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
                   return;
              case IdxColonel:  
                   if (teamSoldier[BluTeam][IdxColonel ] <= 0) {
                       bBluTitle03.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle03.setText("->" + teamSoldier[BluTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
                   return;
              case IdxMajor:  
                   if (teamSoldier[BluTeam][IdxMajor     ] <= 0) {
                       bBluTitle04.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle04.setText("->" + teamSoldier[BluTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
                   return;
              case IdxCaptain:  
                   if (teamSoldier[BluTeam][IdxCaptain   ] <= 0) {
                       bBluTitle05.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle05.setText("->" + teamSoldier[BluTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
                   return;
              case IdxLieutenant:  
                   if (teamSoldier[BluTeam][IdxLieutenant] <= 0) {
                       bBluTitle06.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle06.setText("->" + teamSoldier[BluTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
                   return;
              case IdxSeargant:  
                   if (teamSoldier[BluTeam][IdxSeargant] <= 0) {
                       bBluTitle07.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle07.setText("->" + teamSoldier[BluTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
                   return;
              case IdxMiner:  
                   if (teamSoldier[BluTeam][IdxMiner   ] <= 0) {
                       bBluTitle08.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle08.setText("->" + teamSoldier[BluTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
                   return;
              case IdxScout:  
                   if (teamSoldier[BluTeam][IdxScout   ] <= 0) {
                       bBluTitle09.setEnabled(false);
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                   }
                   bBluTitle09.setText("->" + teamSoldier[BluTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
                   return;
              case IdxSpy:  
                   if (teamSoldier[BluTeam][IdxSpy     ] <= 0) {
                       while (teamSoldier[BluTeam][i] == 0) {highestLiveBluRank = valEntity[i++];}
                       bBluTitle10.setEnabled(false);
                   }
                   bBluTitle10.setText("->" + teamSoldier[BluTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
                   return;
              case IdxBomb:  
                   if (teamSoldier[BluTeam][IdxBomb    ] <= 0) {
                       bBluTitle11.setEnabled(false);
                   }
                   bBluTitle11.setText("->" + teamSoldier[BluTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
                   return;
              case IdxFlag:  
                   if (teamSoldier[BluTeam][IdxFlag    ] <= 0) {
                       bBluTitle12.setEnabled(false);
                   }
                   bBluTitle12.setText("->" + teamSoldier[BluTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);
                   return;
            }
         }    
    } // end of trayUpdate

    private void beepDefused() {
          try {
            AudioInputStream audiosourceDefused = AudioSystem.getAudioInputStream(soundDefused);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceDefused.getFormat());
            clipDefused = (Clip)AudioSystem.getLine(info);
            clipDefused.open(audiosourceDefused);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipDefused.start();
     }

    private void beepAttrition() {
          try {
            AudioInputStream audiosourceAttrition = AudioSystem.getAudioInputStream(soundAttrition);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceAttrition.getFormat());
            clipAttrition = (Clip)AudioSystem.getLine(info);
            clipAttrition.open(audiosourceAttrition);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipAttrition.start();
     }

    private void beepMoved() {
          try {
            AudioInputStream audiosourceMoved = AudioSystem.getAudioInputStream(soundMoved);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceMoved.getFormat());
            clipMoved = (Clip)AudioSystem.getLine(info);
            clipMoved.open(audiosourceMoved);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipMoved.start();
     }

    private void beepBluMoved() {
          try {
            AudioInputStream audiosourceBluMoved = AudioSystem.getAudioInputStream(soundBluMoved);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceBluMoved.getFormat());
            clipBluMoved = (Clip)AudioSystem.getLine(info);
            clipBluMoved.open(audiosourceBluMoved);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipBluMoved.start();
     }

    private void beepError() {
          try {
            AudioInputStream audiosourcePieceErr = AudioSystem.getAudioInputStream(soundPieceErr);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourcePieceErr.getFormat());
            clipPieceErr = (Clip)AudioSystem.getLine(info);
            clipPieceErr.open(audiosourcePieceErr);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipPieceErr.start();
     }

    private void beepExplode() {
          try {
            AudioInputStream audiosourceExplode = AudioSystem.getAudioInputStream(soundExplode);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceExplode.getFormat());
            clipExplode = (Clip)AudioSystem.getLine(info);
            clipExplode.open(audiosourceExplode);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipExplode.start();
     }

    private void beepAdd() {
          try {
            AudioInputStream audiosourcePieceAdd = AudioSystem.getAudioInputStream(soundPieceAdd);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourcePieceAdd.getFormat());
            clipPieceAdd = (Clip)AudioSystem.getLine(info);
            clipPieceAdd.open(audiosourcePieceAdd);
          }
          catch(UnsupportedAudioFileException es) {}
          catch(LineUnavailableException es) {}
          catch(IOException es) {}

          clipPieceAdd.start();
    }

    private void beepRmv() {
          try {
            AudioInputStream audiosourcePieceRmv = AudioSystem.getAudioInputStream(soundPieceRmv);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourcePieceRmv.getFormat());
            clipPieceRmv = (Clip)AudioSystem.getLine(info);
            clipPieceRmv.open(audiosourcePieceRmv);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipPieceRmv.start();
    }

    private void beepCaptured() {
          try {
            AudioInputStream audiosourceCaptured = AudioSystem.getAudioInputStream(soundCaptured);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceCaptured.getFormat());
            clipCaptured = (Clip)AudioSystem.getLine(info);
            clipCaptured.open(audiosourceCaptured);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipCaptured.start();
     }

    private void beepCapture() {
          try {
            AudioInputStream audiosourceCapture = AudioSystem.getAudioInputStream(soundCapture);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceCapture.getFormat());
            clipCapture = (Clip)AudioSystem.getLine(info);
            clipCapture.open(audiosourceCapture);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipCapture.start();
     }

    private void beepWin() {
          try {
            AudioInputStream audiosourceWin = AudioSystem.getAudioInputStream(soundWin);
            DataLine.Info info = new DataLine.Info(Clip.class, audiosourceWin.getFormat());
            clipWin = (Clip)AudioSystem.getLine(info);
            clipWin.open(audiosourceWin);
          }
          catch(UnsupportedAudioFileException e) {}
          catch(LineUnavailableException e) {}
          catch(IOException e) {}

          clipWin.start();
     }

    private void unselectAll() {
            bRedTitle01.setSelected(false);
            bRedTitle02.setSelected(false);
            bRedTitle03.setSelected(false);
            bRedTitle04.setSelected(false);
            bRedTitle05.setSelected(false);
            bRedTitle06.setSelected(false);
            bRedTitle07.setSelected(false);
            bRedTitle08.setSelected(false);
            bRedTitle09.setSelected(false);
            bRedTitle10.setSelected(false);
            bRedTitle11.setSelected(false);
            bRedTitle12.setSelected(false);
    }
   
    private void updateButtonText (int c, boardSpot bs) {
        if (c > 0) { // putting a piece back onto tray
            bRedTitle01.setSelected(false);
            bRedTitle02.setSelected(false);
            bRedTitle03.setSelected(false);
            bRedTitle04.setSelected(false);
            bRedTitle05.setSelected(false);
            bRedTitle06.setSelected(false);
            bRedTitle07.setSelected(false);
            bRedTitle08.setSelected(false);
            bRedTitle09.setSelected(false);
            bRedTitle10.setSelected(false);
            bRedTitle11.setSelected(false);
            bRedTitle12.setSelected(false);
            switch(bs.getWho()) {
              case IdxMarshall:   //0
                   bRedTitle01.setSelected(true);
                   break;
              case IdxGeneral:    //1
                   bRedTitle02.setSelected(true);
                   break;
              case IdxColonel:    //2
                   bRedTitle03.setSelected(true);
                   break;
              case IdxMajor:     //3 
                   bRedTitle04.setSelected(true);
                   break;
              case IdxCaptain:     //4
                   bRedTitle05.setSelected(true);
                   break;
              case IdxLieutenant:  //5
                   bRedTitle06.setSelected(true);
                   break;
              case IdxSeargant:    //6
                   bRedTitle07.setSelected(true);
                   break;
              case IdxMiner:      //7
                   bRedTitle08.setSelected(true);
                   break;
              case IdxScout:      //8
                   bRedTitle09.setSelected(true);
                   break;
              case IdxSpy:       //9
                   bRedTitle10.setSelected(true);
                   break;
              case IdxBomb:  
                   bRedTitle11.setSelected(true);
                   break;
              case IdxFlag:  
                   bRedTitle12.setSelected(true);
                   break;
            }
        }
        totalAssigned -= c;
        if (totalAssigned >= totalPieces) {
            jbStart.setEnabled(true);
            animationStatus = NewGameFullBoard;
        }
        else
            jbStart.setEnabled(false);

        if (totalAssigned <= 0)
            animationStatus = NewGameEmptyBoard;
        if (totalAssigned > 0 && totalAssigned < totalPieces)
            animationStatus = NewGamePartialBoard;

        if (bRedTitle01.isSelected()) {
            teamSoldier[RedTeam][IdxMarshall  ] += c;
            if (teamSoldier[RedTeam][IdxMarshall] <= 0) {
                bRedTitle01.setSelected(false);
                bRedTitle01.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle01.setEnabled(true);
            }            
            bRedTitle01.setText(teamSoldier[RedTeam][IdxMarshall  ] + "-" + strDesc[IdxMarshall  ]);
            return;
        }
        if (bRedTitle02.isSelected()) {
            teamSoldier[RedTeam][IdxGeneral   ] += c;
            if (teamSoldier[RedTeam][IdxGeneral ] <= 0) {
                bRedTitle02.setSelected(false);
                bRedTitle02.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle02.setEnabled(true);
            }            
            bRedTitle02.setText(teamSoldier[RedTeam][IdxGeneral   ] + "-" + strDesc[IdxGeneral   ]);
            return;
        }
        if (bRedTitle03.isSelected()) {
            teamSoldier[RedTeam][IdxColonel   ] += c;
            if (teamSoldier[RedTeam][IdxColonel ] <= 0) {
                bRedTitle03.setSelected(false);
                bRedTitle03.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle03.setEnabled(true);
            }            
            bRedTitle03.setText(teamSoldier[RedTeam][IdxColonel   ] + "-" + strDesc[IdxColonel   ]);
            return;
        }
        if (bRedTitle04.isSelected()) {
            teamSoldier[RedTeam][IdxMajor     ] += c;
            if (teamSoldier[RedTeam][IdxMajor   ] <= 0) {
                bRedTitle04.setSelected(false);
                bRedTitle04.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle04.setEnabled(true);
            }            
            bRedTitle04.setText(teamSoldier[RedTeam][IdxMajor     ] + "-" + strDesc[IdxMajor     ]);
            return;
        }
        if (bRedTitle05.isSelected()) {
            teamSoldier[RedTeam][IdxCaptain   ] += c;
            if (teamSoldier[RedTeam][IdxCaptain   ] <= 0) {
                bRedTitle05.setSelected(false);
                bRedTitle05.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle05.setEnabled(true);
            }            
            bRedTitle05.setText(teamSoldier[RedTeam][IdxCaptain   ] + "-" + strDesc[IdxCaptain   ]);
            return;
        }
        if (bRedTitle06.isSelected()) {
            teamSoldier[RedTeam][IdxLieutenant] += c;
            if (teamSoldier[RedTeam][IdxLieutenant] <= 0) {
                bRedTitle06.setSelected(false);
                bRedTitle06.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle06.setEnabled(true);
            }            
            bRedTitle06.setText(teamSoldier[RedTeam][IdxLieutenant] + "-" + strDesc[IdxLieutenant]);
            return;
        }
        if (bRedTitle07.isSelected()) {
            teamSoldier[RedTeam][IdxSeargant  ] += c;
            if (teamSoldier[RedTeam][IdxSeargant] <= 0) {
                bRedTitle07.setSelected(false);
                bRedTitle07.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle07.setEnabled(true);
            }            
            bRedTitle07.setText(teamSoldier[RedTeam][IdxSeargant  ] + "-" + strDesc[IdxSeargant  ]);
            return;
        }
        if (bRedTitle08.isSelected()) {
            teamSoldier[RedTeam][IdxMiner     ] += c;
            if (teamSoldier[RedTeam][IdxMiner   ] <= 0) {
                bRedTitle08.setSelected(false);
                bRedTitle08.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle08.setEnabled(true);
            }            
            bRedTitle08.setText(teamSoldier[RedTeam][IdxMiner     ] + "-" + strDesc[IdxMiner     ]);
            return;
        }
        if (bRedTitle09.isSelected()) {
            teamSoldier[RedTeam][IdxScout     ] += c;
            if (teamSoldier[RedTeam][IdxScout   ] <= 0) {
                bRedTitle09.setSelected(false);
                bRedTitle09.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle09.setEnabled(true);
            }            
            bRedTitle09.setText(teamSoldier[RedTeam][IdxScout     ] + "-" + strDesc[IdxScout     ]);
            return;
        }
        if (bRedTitle10.isSelected()) {
            teamSoldier[RedTeam][IdxSpy       ] += c;
            if (teamSoldier[RedTeam][IdxSpy     ] <= 0) {
                bRedTitle10.setSelected(false);
                bRedTitle10.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle10.setEnabled(true);
            }            
            bRedTitle10.setText(teamSoldier[RedTeam][IdxSpy       ] + "-" + strDesc[IdxSpy       ]);
            return;
        }
        if (bRedTitle11.isSelected()) {
            teamSoldier[RedTeam][IdxBomb      ] += c;
            if (teamSoldier[RedTeam][IdxBomb    ] <= 0) {
                bRedTitle11.setSelected(false);
                bRedTitle11.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle11.setEnabled(true);
            }            
            bRedTitle11.setText(teamSoldier[RedTeam][IdxBomb      ] + "-" + strDesc[IdxBomb      ]);
            return;
        }
        if (bRedTitle12.isSelected()) {
            teamSoldier[RedTeam][IdxFlag      ] += c;
            if (teamSoldier[RedTeam][IdxFlag    ] <= 0) {
                bRedTitle12.setSelected(false);
                bRedTitle12.setEnabled(false);
                pieceSelected = -1;
            }
            else {
                bRedTitle12.setEnabled(true);
            }
            bRedTitle12.setText(teamSoldier[RedTeam][IdxFlag      ] + "-" + strDesc[IdxFlag      ]);
            return;
        }
    }
    private int getSelectedPiece() {
        if (bRedTitle01.isSelected()) return IdxMarshall;
        if (bRedTitle02.isSelected()) return IdxGeneral;
        if (bRedTitle03.isSelected()) return IdxColonel;
        if (bRedTitle04.isSelected()) return IdxMajor;
        if (bRedTitle05.isSelected()) return IdxCaptain;
        if (bRedTitle06.isSelected()) return IdxLieutenant;
        if (bRedTitle07.isSelected()) return IdxSeargant;
        if (bRedTitle08.isSelected()) return IdxMiner;
        if (bRedTitle09.isSelected()) return IdxScout;
        if (bRedTitle10.isSelected()) return IdxSpy;  
        if (bRedTitle11.isSelected()) return IdxBomb;
        if (bRedTitle12.isSelected()) return IdxFlag; 
        return(-1);
    }

    private boardSpot coordinateTest(int x, int y) {
        boardSpot bs = findMatchingSpot(x, y);
        if (bs == null) {

            beepError();
            String dialogTitle = "Game Set Up Error";
            String dialogMsg   = "This spot is off the board";
            int dialogType     = JOptionPane.ERROR_MESSAGE;
            JOptionPane.showMessageDialog((Component) null, dialogMsg, dialogTitle, dialogType);
            return(null);
        }
        if (bs.isOccupied()) {
            beepError();
            String dialogTitle = "Game Set Up Error";
            String dialogMsg   = "This spot is already filled!";
            int dialogType     = JOptionPane.ERROR_MESSAGE;
            JOptionPane.showMessageDialog((Component) null, dialogMsg, dialogTitle, dialogType);
            return(null);
        }
        if (!bs.isLand()) {
            beepError();
            String dialogTitle = "Game Set Up Error";
            String dialogMsg   = "This spot is not land!";
            int dialogType     = JOptionPane.ERROR_MESSAGE;
            JOptionPane.showMessageDialog((Component) null, dialogMsg, dialogTitle, dialogType);
            return(null);
        }
        if (bs.getStartSlot() != RedTeam) {

            System.out.println(bs.getOrdX() + "." + bs.getOrdY() + "->selected"); 
            for (int a = 9; a <= 9; a++) 
                 for(int b = 0; b <= 9; b++)
                     if (board[a][b].getStartSlot() == BluTeam) System.out.println(a + "." + b + "->Blue"); 
                     else
                     if (board[a][b].getStartSlot() == RedTeam) System.out.println(a + "." + b + "->Red"); 
                     else
                         System.out.println(a + "." + b + "->none"); 

            beepError();
            String dialogTitle = "Game Set Up Error";
            String dialogMsg   = "This spot is not valid for the Red Team!";
            int dialogType     = JOptionPane.ERROR_MESSAGE;
            JOptionPane.showMessageDialog((Component) null, dialogMsg, dialogTitle, dialogType);
            return(null);
        }
        return(bs);         
    }

    // This function return x/y offset of a source boardSpot to a destination boardSpot
    private Dimension getVector(boardSpot fm, boardSpot to) {

         int xOffset = fm.getOrdX() - to.getOrdX();
         if (xOffset < 0) xOffset = -xOffset;

         int yOffset = fm.getOrdY() - to.getOrdY();
         if (yOffset < 0) yOffset = -yOffset;

         return new Dimension(xOffset, yOffset);
    } // getVector

    private void dbgBoard() {
        for (int x = 0; x < ROWMAX; x++) { 
             for (int y = 0; y < COLMAX; y++) {
                  if (board[x][y].isLand() && x == 3)
                      System.out.println("dbg: " + board[x][y].toString());
             }
        }
    }
    // The x,y coordinates stored in each boardSpot object is offset from the top-left of the
    // animpationPanel and this is not compatible with the x,y coordinates derived from the 
    // mouse click activity because the mouse click activity is offset from the top-left of
    // the entire window immediately below the WINDOWS TITLE BAR. Therefore, at initial startup
    // the height and width of the top-left corner of the animation Panel is known to be 152,122
    // and this is used in the calculations to find matching boardSpot coordinates based on mouse
    // clicks. When it is discovered how to find the starting offset of the animationPanel
    // without hardcoding, that will be used instead.
    private boardSpot findMatchingSpot(int xM, int yM) {
         int startH = 152;  // when it is discovered how to get real hieght 
         int startW = 122;
         int xc, yc = 0; 
         for (int x = 0; x < ROWMAX; x++) { 
              for (int y = 0; y < COLMAX; y++) {

                   xc = xM - startW; yc = yM - startH;

                   if ((xc >= board[x][y].getXTop()) &&
                       (yc >= board[x][y].getYTop()) &&
                       (xc <= board[x][y].getXBot()) &&
                       (yc <= board[x][y].getYBot())) {
                        //System.out.println("findMatchingSpot: mouse clicked at " + board[x][y].toDiagString() + " is " + x + "." + y);
                        return(board[x][y]); 
                   }
              }
         }
         return(null); // cant find it?!
    } // end of findMatchingBoardSpot

    // If all red/blu moveable pieces are captured - red/blu loses
    private int countMoveable(int t) {
        int count = 0;
        for (int x = 0; x < ROWMAX; x++) {
            for (int y = 0; y < COLMAX; y++) {
                if (board[x][y].getTeam() == t && 
                    board[x][y].isMoveable()) {
                    count++;
                }
            }
        }
        return count; 
    }

    private boolean isJumpingSpots(Dimension v, int x1, int y1, int x2, int y2) {
         //System.out.print("IJS: v=" + v.width + "." + v.height);
         if (v.width > 1) {
             //System.out.print("IJS: horizontal fm=" + x1 + "." + y1 + " to=" + x2 + "." + y2);
             if (x1 > x2) {
                 int t = x1;
                 x1 = x2;
                 x2 = t;
             }
             x1++;
             while (x1 != x2) {
                  if (board[x1++][y1].isOccupied())  {
                      //System.out.println("  ... jumping over");
                      return true; }
             }
         } // end of horizontal move check

         if (v.height > 1) {
             //System.out.print("IJS: vertical fm=" + x1 + "." + y1 + " to=" + x2 + "." + y2);
             if (y1 > y2) {
                 int t = y1;
                 y1 = y2;
                 y2 = t;
             }
             y1++;
             while (y1 != y2) {
                  if (board[x1][y1++].isOccupied()) { 
                      //System.out.println("  ... jumping over");
                      return true; }
             }
         } // end of verical move check
         //System.out.println("  ...not jumping over");
         return false;
    } // end of isJumpingSpots

    public void stateChanged(ChangeEvent e) {}

    public void mousePressed(MouseEvent e) {
           //System.out.println("mousePressed EVENT"); 
           if (animationStatus >= RunningGame) 
               return;

           // If the right button is clicked with mouse pointing at Red placed piece, it removes the piece
           // from the board, and increments the available count in the selection vertical row of control buttons
           if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
                //System.out.println("mousePressed-1: BUTTON3"); 
                boardSpot bs = findMatchingSpot(e.getX(), e.getY());
                if (bs == null) {
                    beepError();
                    String dialogTitle = "mouseClicked (RIGHT BUTTON)) Error";
                    String dialogMsg   = "This spot is off the board";
                    int dialogType     = JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog((Component) null, dialogMsg, dialogTitle, dialogType);
                    return;
                }

                if (!bs.isOccupied()) return;  // Cant restore piece to "Tray" if selecting nothing
                if (bs.getTeam() == BluTeam) return; // Cant restore the blut team's pieces]

                boolean f = false;
                for (int t=0; t<40; t++) {
                     if (team[RedTeam][t] == bs.getPiece()) {
                         // found piece to be removed
                         f = true;
                         updateButtonText(+1, bs);
                         team[RedTeam][t].init();
                         bs.setOccupied(false); 
                         beepRmv();
                     }
                }

                if (bs.isOccupied()) {
                    beepError();
                    String dialogTitle = "mouseClicked (RIGHT BUTTON)) Error";
                    String dialogMsg   = "Cannot find piece to be removed!";
                    int dialogType     = JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog((Component) null, dialogMsg, dialogTitle, dialogType);
                    return;
                }
                repaint();
                return; 
           }
    } // end of mousePressed

    public void mouseClicked(MouseEvent e) {

           if (animationStatus >= RunningGame) 
               return;

           if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK)
               return; // This process of moving pieces to the board is effective only for left button click


           boardSpot selectedSpot = coordinateTest(e.getX(), e.getY());
           if (selectedSpot == null)  {
               // emit error sound & do nothing
               beepError();
           }
           else { 
               // Assign the clicked team piece to the coordinate selected
               // Find the next unused team piece of clicked rank
               // Mark it occupied
               // decrement available count for team piece of clicked rank
               // redisplay the button with updated caption 
               // emit good sound
               //System.out.println("MouseClicked: coordinate=>" + bs.toCoordString());???
               int x, y, t = 0;
               boolean done = false;
               int selectedPiece = getSelectedPiece();
               //System.out.println("mouseClicked-> selectedPiece=" + selectedPiece);

               if (selectedPiece < 0) {
                   beepError();
                   String dialogTitle = "Game Set Up Error";
                   String dialogMsg   = "Select a piece from red tray at left to place on the board!";
                   int dialogType     = JOptionPane.ERROR_MESSAGE;
                   JOptionPane.showMessageDialog((Component) null, dialogMsg, dialogTitle, dialogType);
                   return;
               }

               while (!done && t < 40) {                                  // use this as template for positioning RedTeam?
                       if (!team[RedTeam][t].isAssignedToBoard() && 
                            team[RedTeam][t].getWho() == selectedPiece) {
                            x = selectedSpot.getOrdX();
                            y = selectedSpot.getOrdY();
                            board[x][y].setPiece(team[RedTeam][t]);
                            board[x][y].setOccupied(true);
                            team[RedTeam][t].setAssignedToBoard(true);

                            updateButtonText(-1, board[x][y]);
                            beepAdd();
                            repaint();
                            done = true;
                       }
                       else t++;
                }
                if (!done){
                    beepError();
                    System.out.println("mouseClicked: error#1: cant find matching piece is set");
                    System.exit(0);
                } 
           } // else
    } // end of mouseClicked

    public void mouseMoved(MouseEvent e)   {} //{System.out.println("mouseMoved EVENT"); }
    public void mouseEntered(MouseEvent e)  {} //{System.out.println("mouseEntered EVENT"); }
    public void mouseExited(MouseEvent e)   {} //{System.out.println("mouseExited EVENT"); }

    public void mouseDragged(MouseEvent e)  {  //{System.out.println("mouseDragged EVENT"); }
        if (animationStatus < RunningGame)
            // dragging only allowed during game play
            return;

        // First time here? If so, the dragging has begun - save initial boardSpot
        if (bsDraggedFrom == null) {
            //System.out.print("mouseDragged: grabbed...");
            bsDraggedFrom = findMatchingSpot(e.getX(), e.getY());
            if (bsDraggedFrom == null) {
                 return;
            }   
            if (!bsDraggedFrom.isOccupied() || bsDraggedFrom.getTeam() != RedTeam) {
                 bsDraggedFrom = null;
                 return;
            }   
            if (!bsDraggedFrom.isMoveable()) {
                 bsDraggedFrom = null;
                 return;
            }   
            return;
        }
        //System.out.print("mouseDragged-2:");
        //System.out.println("mouseDragged: dragging..." + bsDraggedFrom.getPiece().toString());
    } // end of mouseDragged

    public void mouseReleased(MouseEvent e) { //{System.out.println("mouseReleased EVENT"); }
        if (animationStatus < RunningGame)
            // dragging only allowed during game play
            return;

        if (animationStatus != RunningGameRedTurn || drawing)
            // dont let red move before it is his turn
            return;

        if (bsDraggedFrom == null) {
            bsDraggedTo = null; 
            return;   
        }

        // No piece at "from" boardSpot
        if (bsDraggedFrom.getPiece() == null) {
            //System.out.println(" invalid-0"); 
            bsDraggedFrom = null;
            bsDraggedTo = null; 
            return;
        }

        // Is this destination boardSpot valid? 
        bsDraggedTo = findMatchingSpot(e.getX(), e.getY());
        if (bsDraggedTo == null) { 
            bsDraggedTo = null; 
            bsDraggedFrom = null; 
            beepError();
            return; 
        }

        // If this destination boardSpot is occupied already by Red, it is NOT a valid destination
        // If this destination boardSpot is occupied already by Blue, it is a valid destination (Attack)
        if (bsDraggedTo.isOccupied() && bsDraggedTo.getTeam() == RedTeam) {
            bsDraggedFrom = null;
            bsDraggedTo = null; 
            beepError();
            return;
        }

        // If this destination boardSpot is water, it isnt a valid destination
        if (!bsDraggedTo.isLand()) {
            bsDraggedFrom = null;
            bsDraggedTo = null; 
            beepError();
            return;
        }

        // get x,y distance from initial point of dragging to this spot
        Dimension vec = getVector(bsDraggedFrom, bsDraggedTo);
        // is it ok for this piece to move distance implied by this destination?
        if (! bsDraggedFrom.getPiece().isProperMove(vec)) {
            bsDraggedFrom = null;
            bsDraggedTo = null; 
            beepError();
            return;
        }

        // Verify scout isnt jumping over a piece to move to any destination
        if (isJumpingSpots(vec, bsDraggedFrom.getOrdX(), bsDraggedFrom.getOrdY(),
                              bsDraggedTo.getOrdX(),   bsDraggedTo.getOrdY())) {
            bsDraggedFrom = null;
            bsDraggedTo = null; 
            beepError();
            return;
        }

        // valid destination for dragging!
        jbStart.setEnabled(false);
        int xTo = bsDraggedTo.getOrdX();
        int yTo = bsDraggedTo.getOrdY();
        int xFm = bsDraggedFrom.getOrdX();
        int yFm = bsDraggedFrom.getOrdY();

        // Red moves to an unoccupied spot - no attack
        if (!bsDraggedTo.isOccupied()) {

             // if red moves a piece more than 1 spot in distance, it is obviously a scout - that exposes it
             boolean scout = false;
             int xChg = board[xTo][yTo].getOrdX() - board[xFm][yFm].getOrdX();
             int yChg = board[xTo][yTo].getOrdY() - board[xFm][yFm].getOrdY();
             if (xChg > 1 || xChg < -1) scout = true;
             if (yChg > 1 || yChg < -1) scout = true;

             board[xTo][yTo].setPiece(bsDraggedFrom.getPiece());
             board[xTo][yTo].setOccupied(true);
             board[xTo][yTo].setMoved(true);
             board[xTo][yTo].setExposed(board[xFm][yFm].isExposed());
             if (scout) 
                 board[xTo][yTo].setExposed(true);

             bsDraggedFrom.setOccupied(false);
             board[xFm][yFm].setPiece(null);
             board[xFm][yFm].setOccupied(false);
             board[xFm][yFm].setMoved(false);

             bsDraggedFrom = null;
             bsDraggedTo = null; 

             beepMoved();
             repaint();
             animationStatus = RunningGameBluTurn;
             return;
        }

        // Attack is made upon Blue...the losing piece will be vapored to its own button tray
        if (bsDraggedTo.isOccupied() && bsDraggedTo.getTeam() == BluTeam) {
            //System.out.print("MouseReleased - attack..."); 
            if (board[xTo][yTo].getPiece().getRank() < board[xFm][yFm].getPiece().getRank() ||  // red attacker wins
                board[xFm][yFm].getPiece().getWho() == IdxMiner && board[xTo][yTo].getPiece().getWho() == IdxBomb ||
                board[xFm][yFm].getPiece().getWho() == IdxSpy   && board[xTo][yTo].getPiece().getWho() == IdxMarshall) {
                //System.out.println("defenderRank=" + board[xTo][yTo].getPiece().getRank() + 
                //                ", attackerRank=" + board[xFm][yFm].getPiece().getRank());
                if (board[xTo][yTo].getPiece().getWho() == IdxBomb)
                    beepDefused();
                else
                    beepCapture();
                winningPic  = Toolkit.getDefaultToolkit().getImage(board[xFm][yFm].getImage());
                winningX    = board[xTo][yTo].getXTop();
                winningY    = board[xTo][yTo].getYTop();
                losingWhoRed = NoOne;
                losingWhoBlu = board[xTo][yTo].getPiece().getWho();
                losingPic1  = Toolkit.getDefaultToolkit().getImage(board[xTo][yTo].getImage());
                losingBluX  = board[xTo][yTo].getXTop();
                losingBluY  = board[xTo][yTo].getYTop();
                board[xTo][yTo].setPiece(bsDraggedFrom.getPiece());  
                board[xTo][yTo].setOccupied(true);                  
                board[xTo][yTo].setExposed(true);
                board[xTo][yTo].setMoved(true);
                board[xFm][yFm].setPiece(null);
                board[xFm][yFm].setOccupied(false);
                board[xFm][yFm].setExposed(false);
                board[xFm][yFm].setMoved(false);
                winner = RedTeam;
                // identity of losing blue piece is hard to see if it is close to tray and it vapors too fast
                if (board[xTo][yTo].getOrdY() > 7) chgVapor = 2; 
                else  chgVapor = 5;
                delayMillis = 50;
                drawing = true;
            }
            else
            if (board[xTo][yTo].getPiece().getRank() > board[xFm][yFm].getPiece().getRank()) { // blue defender wins
                //System.out.println("defenderRank=" + board[xTo][yTo].getPiece().getRank() + 
                //                 ", attackerRank=" + board[xFm][yFm].getPiece().getRank());
                winningPic  = Toolkit.getDefaultToolkit().getImage(board[xTo][yTo].getImage());
                winningX    = board[xTo][yTo].getXTop();
                winningY    = board[xTo][yTo].getYTop();
                losingWhoRed = board[xFm][yFm].getPiece().getWho();
                losingWhoBlu = NoOne;
                losingPic1  = Toolkit.getDefaultToolkit().getImage(board[xFm][yFm].getImage());
                losingRedX  = board[xTo][yTo].getXTop();
                losingRedY  = board[xTo][yTo].getYTop();
                board[xTo][yTo].setPiece(bsDraggedTo.getPiece());  // no change really
                board[xTo][yTo].setOccupied(true);                 // no change really 
                board[xTo][yTo].setExposed(true);
                board[xFm][yFm].setPiece(null);
                board[xFm][yFm].setOccupied(false);
                board[xFm][yFm].setExposed(false);
                board[xFm][yFm].setMoved(false);
                winner = BluTeam;
                // identity of losing red piece is hard to see if it is close to tray and it vapors too fast
                if (board[xTo][yTo].getOrdY() < 2) chgVapor = 2; 
                else chgVapor = 5;
                delayMillis = 50;
                drawing = true;
                if (board[xTo][yTo].getPiece().getWho() == IdxBomb) 
                    beepExplode();
                else
                    beepCaptured();
            }
            else {                                                                             // both lose
                //System.out.println("defenderRank=" + board[xTo][yTo].getPiece().getRank() + 
                //                ", attackerRank=" + board[xFm][yFm].getPiece().getRank());
                losingWhoRed = board[xFm][yFm].getPiece().getWho();
                losingWhoBlu = board[xTo][yFm].getPiece().getWho();
                losingPic1  = Toolkit.getDefaultToolkit().getImage(board[xTo][yTo].getImage());
                losingPic2  = Toolkit.getDefaultToolkit().getImage(board[xFm][yFm].getImage());
                losingRedX = board[xTo][yTo].getXTop();
                losingRedY = board[xTo][yTo].getYTop();
                losingBluX = board[xTo][yTo].getXTop();
                losingBluY = board[xTo][yTo].getYTop();
                board[xTo][yTo].setPiece(null);
                board[xTo][yTo].setOccupied(false);
                board[xTo][yTo].setExposed(false);
                board[xTo][yTo].setMoved(false);
                board[xFm][yFm].setPiece(null);
                board[xFm][yFm].setOccupied(false);
                board[xFm][yFm].setExposed(false);
                board[xFm][yFm].setMoved(false);
                winner = TieTeam;
                delayMillis = 50;
                drawing = true;
                beepAttrition();
                //dbgBoard();
            }
            repaint(); 
            return;
        }
        return;
    } // end of mouseReleased

    public void itemStateChanged(ItemEvent e) {}

    class AnimationPanel extends JPanel {
         AnimationPanel() {
                setBackground(Color.white);
                Dimension dim = getPreferredSize();
                setPreferredSize(dim); 
         }
         public Dimension getPreferredSize() {
                return new Dimension(510, 510);
         }
         public void paintComponent(Graphics g) {
             super.paintComponent(g);

             int side = majorPanelSize.width / COLMAX / 10 * 7;
             int xOffset, yOffset = 0;
             int pieceWidth = 0; 
             int pieceHeight = 0;
             int insetX = 0;
             int insetY = 0;
             Image pic;

             //if (!drawing ) {
             for (int x = 0; x < ROWMAX; x++) {
                  xOffset = 0;
                  for (int y = 0; y < COLMAX; y++) {
                      board[x][y].setLocation(xOffset, yOffset, xOffset + side, yOffset + side);

                      g.setColor(Color.black);
                      g.drawRect(xOffset, yOffset, side, side);
                      if (!board[x][y].isLand()) {
                          g.setColor(water);
                          g.fillRect(xOffset, yOffset, side, side); 
                      }
                      // 0=> draw each team's image into the proper coordinates, identity hidden
                      // 5=> draw each team's image into the proper coordinates, identity exposed
                      board[x][y].toString(); // for debugging purposes...

                      if (board[x][y].isOccupied()) {
                          pieceWidth = side * 7 / 10; 
                          pieceHeight = side * 8 / 10;
                          insetX = (side - pieceWidth) / 2;
                          insetY = (side - pieceHeight) / 2;

                          if (!exposeOpponent && board[x][y].getTeam() == BluTeam) {
                               pic  = Toolkit.getDefaultToolkit().getImage("_BlueHidden.gif");
                               g.drawImage(pic, insetX+xOffset, insetY+yOffset, pieceWidth, pieceHeight, this);
                          }
                          else {
                               pic  = Toolkit.getDefaultToolkit().getImage(board[x][y].getImage());
                               g.drawImage(pic, insetX+xOffset, insetY+yOffset, pieceWidth, pieceHeight, this);
                          }
                      }
                      if (!board[x][y].isOccupied()) {
                          if (board[x][y].getTrail() != null) {
                               pic  = board[x][y].getTrail();
                               g.drawImage(pic, insetX+xOffset, insetY+yOffset, pieceWidth, pieceHeight, this);
                          }
                      }
                      xOffset = xOffset + side; 
                  } 
                  yOffset = yOffset + side; 
             }
            // }

             if (winner == RedTeam) {
                 int px = losingBluY + insetY;
                 int py = losingBluX;
                 losingBluX += chgVapor;
                 g.drawImage(losingPic1, losingBluX, losingBluY + insetY, pieceWidth, pieceHeight, this);
                 // display the winning piece momentarily, until the red piece is retired in its tray
                 g.drawImage(winningPic, insetX + winningX, insetY + winningY, pieceWidth, pieceHeight, this);
                 if (losingBluX > board[0][9].getXBot() / 2) chgVapor = 5; // speed it up after it is in the middle
             }
             if (winner == BluTeam) {
                 losingRedX -= chgVapor;
                 g.drawImage(losingPic1, losingRedX, losingRedY + insetY, pieceWidth, pieceHeight, this);
                 // display the winning piece momentarily. The piece identity is hidden in each repaint, but
                 // this statement here, overlays the spot the true identity. After the captured pieces are
                 // retired, these overlays no longer occur.
                 g.drawImage(winningPic, insetX + winningX, insetY + winningY, pieceWidth, pieceHeight, this);
                 if (losingRedX < board[0][9].getXBot() / 2) chgVapor = 5; // speed it up after it is in the middle
             }
             if (winner == TieTeam) {
                 if (losingBluX < board[0][9].getXBot()) {
                     losingBluX += chgVapor;
                     g.drawImage(losingPic1, losingBluX, losingBluY + insetY, pieceWidth, pieceHeight, this);
                 }
                 if (losingRedX > 0) {
                     losingRedX -= chgVapor;
                     g.drawImage(losingPic2, losingRedX, losingRedY + insetY, pieceWidth, pieceHeight, this);
                 }
                 if (losingRedX < board[0][9].getXBot() / 2) chgVapor = 5; // speed it up after it is in the middle
             }
             if ((losingRedX <= 0 && winner == BluTeam) ||
                 (losingBluX >= board[0][9].getXBot() && winner == RedTeam) ||
                 (losingBluX >= board[0][9].getXBot() && losingRedX <= 0 && winner == TieTeam)) {
                 trayUpdate(-1, BluTeam, losingWhoBlu);
                 trayUpdate(-1, RedTeam, losingWhoRed);

                 int cntR = countMoveable(RedTeam); 
                 int cntB = countMoveable(BluTeam); 
                 if (cntR == 0 && cntB  > 0)
                     animationStatus = CompleteGameBluWins;
                 if (cntR  > 0 && cntB == 0)
                     animationStatus = CompleteGameRedWins;
                 if (cntR == 0 && cntB == 0)
                     animationStatus = CompleteGameDraw;
                 if (losingWhoRed == IdxFlag || countMoveable(RedTeam) == 0) 
                     animationStatus = CompleteGameBluWins;
                 if (losingWhoBlu == IdxFlag || countMoveable(BluTeam) == 0) 
                     animationStatus = CompleteGameRedWins;
                 if (countMoveable(RedTeam) == 0) 
                     animationStatus = CompleteGameBluWins;

                 losingPic1 = null;
                 losingPic2 = null;
                 losingRedX = -1;
                 losingRedY = -1;
                 losingBluX = -1;
                 losingBluY = -1;
                 winner     = NoTeam;
                 losingWhoBlu = NoOne;
                 losingWhoRed = NoOne;
                 delayMillis = 2000;
                 drawing = false;

                 if (animationStatus == RunningGameBluTurn) {
                     animationStatus = RunningGameRedTurn; System.out.println("flipping to Red turn");
                 }
                 else
                 if (animationStatus == RunningGameRedTurn) {
                     animationStatus = RunningGameBluTurn; System.out.println("flipping to Blue turn");
                 }
             }
         } // end of paintComponent

    } // end of AnimationPanel

    public void componentResized(ComponentEvent e) {
             majorPanelSize = this.getSize(); 
             //System.out.println("window resized - majorPanelSize=" + majorPanelSize);
    }
    public void componentMoved(ComponentEvent e) {
             //System.out.println("window moved");
    }
    public void componentShown(ComponentEvent e) {
             //System.out.println("window shown");
    }
    public void componentHidden(ComponentEvent e) {
             //System.out.println("window hidden");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    class AnimationThread implements Runnable {
         Thread t;
         String gameSummary = "";
         LinkedList moveList = new LinkedList(); // before every blue move, a list of possible moves is built
         LinkedList bombList = new LinkedList(); // Miners direct their motion towards known bombs
         int BluPersonality = (int) (Math.random() * 25);

         AnimationThread() {
             t = new Thread(this);
             t.start(); 
         }
         public void run() {
            try {
                 while (animationStatus < CompleteGame) {
                      if (animationStatus < RunningGame) {
                          Thread.sleep(delayMillis);
                          lTurn.setText("Setup Mode");
                          while (!bombList.isEmpty()) 
                                  bombList.removeFirst();
                          delayMillis = 2000; // after first display of board icons, shorten up sleep
                      }

                      if (animationStatus >= RunningGame) { 
                          //while (!bombList.isEmpty()) 
                          //        bombList.removeFirst();

                          if (animationStatus == RunningGameRedTurn)
                              lTurn.setText("Red's Turn");
                          else
                          if (animationStatus == RunningGameBluTurn)
                              lTurn.setText("Blue's Turn");

                          repaint();

                          Thread.sleep(delayMillis);

                          if (animationStatus == RunningGameBluTurn && !drawing) {
                              playBluMove();
                          }
                      } 
                 }
                 if (animationStatus == CompleteGameRedWins) {
                     beepWin(); 
                     gameSummary = "Game Over - Red Wins!";
                 }
                 if (animationStatus == CompleteGameBluWins)
                     gameSummary = "Game Over - Blue Wins!";
                 if (animationStatus == CompleteGameDraw)
                     gameSummary = "Game Over - Draw!";

                 lTurn.setText(gameSummary);

                 String dialogTitle = "Game Summary";
                 String dialogMsg   = gameSummary + " Play again?";
                 int instruction = JOptionPane.showConfirmDialog((Component) null, dialogMsg, "Game Over", 
                                                                 JOptionPane.YES_NO_OPTION);
                 if (instruction == JOptionPane.YES_OPTION) {
                     System.exit(0);
                 } else {
                     System.exit(0);
                 }
 
            } catch (InterruptedException e) {}
              System.out.println("Thread ending....");
         }

         private void playBluMove() {
              boolean offOverride = false; // sometimes an obvious no-lose offensive situation occurs
              boardSpot goalBS = null;
              nextMove nm = null;
              String choice = "";

              if (teamSoldier[BluTeam][IdxFlag] <= 0) {
                  animationStatus = CompleteGame;
                  return;
              }

              // How do we know which piece will move where? 
              // Build a list of moves from which to pick; if none, Blue loses
              moveList = buildMoveList();
              if (moveList == null) { // this should never happen (here)!
                  animationStatus = CompleteGame;
                  return;
              }
              computeTargetAll();
              goalBS = determineGoal();  // find out what direction is most productive towards winning  
              System.out.println("target of offense moves => " + goalBS.toCoordString());

              // returns true or false if very good offensive moves can be made in this move
              offOverride = determineOffense(moveList, goalBS);  
              System.out.println("offOverride=" + offOverride);

              determineDefense(moveList);
              dbgNextMove(moveList);

              // Determine if this move is offensive or defensive
              int offdef = (int) (Math.random() * 100);
              //System.out.println("BluPers=" + BluPersonality + ", " + "this move rnd#=" + offdef);

               if (offdef > BluPersonality || offOverride) {
                  // offensive - pick the move with the highest attack factor 
                  System.out.print("BluePers=" + BluPersonality + " vs " + offdef + "=off; selected -->");
                  nm = pickMove(1, moveList);
                  moveBluPiece(nm, 1); }
              else {
                  // defensive - pick the move with the highest retreat factor
                  System.out.print("BluePers=" + BluPersonality + " vs " + offdef + "=def; selected -->");
                  nm = pickMove(0, moveList);
                  moveBluPiece(nm, 0); }

              moves++;
              lMoves.setText("Moves=" + moves);
         } // end of playOneStep

         // The move selection logic is complete - perform the move
         private void moveBluPiece(nextMove nm, int type) {
              if (trailSpot != null)
                  trailSpot.setTrail(null);
 
              boardSpot fm = nm.getFmBoardSpot();
              boardSpot to = nm.getToBoardSpot();
              if (type == 0 && nm.getRetreatBoardSpot() != null) {
                  to = nm.getRetreatBoardSpot();
                  nm.setAttack(false);
                  System.out.println("MBP: retreating to " + to.toCoordString());
              }

              int xChg = to.getOrdX() - fm.getOrdX();
              int yChg = to.getOrdY() - fm.getOrdY();

              if (yChg == -1) fm.setTrail(bluLeft);
              if (yChg ==  1) fm.setTrail(bluRight);
              if (xChg == -1) fm.setTrail(bluUp);
              if (xChg ==  1) fm.setTrail(bluDown);
              trailSpot = fm;

              if (!nm.isAttack()) {
                   to.setPiece(fm.getPiece());
                   to.setOccupied(true);
                   to.setMoved(true);

                   fm.setOccupied(false);
                   fm.setPiece(null);
                   fm.setOccupied(false);
                   fm.setMoved(false);
                   beepBluMoved();
                   animationStatus = RunningGameRedTurn; 
                   return;
              }

              if (nm.isAttack()) {
                  if (to.getPiece().getRank() < fm.getPiece().getRank() ||  // blue attacker wins
                      fm.getPiece().getWho() == IdxMiner && to.getPiece().getWho() == IdxBomb ||
                      fm.getPiece().getWho() == IdxSpy   && to.getPiece().getWho() == IdxMarshall) {
                      winningPic  = Toolkit.getDefaultToolkit().getImage(fm.getImage());
                      winningX    = to.getXTop();
                      winningY    = to.getYTop();
                      losingWhoBlu = NoOne;
                      losingWhoRed = to.getPiece().getWho();
                      losingPic1  = Toolkit.getDefaultToolkit().getImage(to.getImage());
                      losingRedX  = to.getXTop();
                      losingRedY  = to.getYTop();

                      // If red captured piece is bomb, remove it from the bomb list
                      if (to.getPiece().getWho() == IdxBomb) {
                          if (bombList.contains(to)) bombList.remove(to);
                          dbgBomb();
                          }

                      // Emit 'defuse' sound if a bomb is captured
                      if (to.getPiece().getWho() == IdxBomb)
                          beepDefused();
                      else
                          beepCaptured();

                      to.setPiece(fm.getPiece());  
                      to.setOccupied(true);                  
                      to.setExposed(true);
                      to.setMoved(true);
                      fm.setPiece(null);
                      fm.setOccupied(false);
                      fm.setExposed(false);
                      fm.setMoved(false);
                      winner = BluTeam;
                      // identity of losing red piece is hard to see if it is close to tray and it vapors too fast
                      if (to.getOrdY() < 2) chgVapor = 2; 
                      else chgVapor = 5;
                      delayMillis = 50;

                      drawing = true;
                 } 
                 else
                 if (to.getPiece().getRank() > fm.getPiece().getRank()) { // red defender wins
                      winningPic  = Toolkit.getDefaultToolkit().getImage(to.getImage());
                      winningX    = to.getXTop();
                      winningY    = to.getYTop();
                      losingWhoBlu = fm.getPiece().getWho();
                      losingWhoRed = NoOne;
                      losingPic1  = Toolkit.getDefaultToolkit().getImage(fm.getImage());
                      losingBluX  = to.getXTop();
                      losingBluY  = to.getYTop();
                      to.setPiece(to.getPiece());  // no change really
                      to.setOccupied(true);        // no change really 
                      to.setExposed(true);
                      fm.setPiece(null);
                      fm.setOccupied(false);
                      fm.setExposed(false);
                      fm.setMoved(false);
                      winner = RedTeam;
                      // identity of losing red piece is hard to see if it is close to tray and it vapors too fast
                      if (to.getOrdY() > 7) chgVapor = 2; 
                      else chgVapor = 5;
                      delayMillis = 50;

                      drawing = true;
                      if (to.getPiece().getWho() == IdxBomb) 
                         beepExplode();
                      else
                         beepCapture();
                      if (to.getPiece().getWho() == IdxBomb) { // all known bombs are added to bomb list
                          bombList.add(to);
                          dbgBomb();
                      }
                 } 
                 else {  // both lose
                      losingWhoBlu = fm.getPiece().getWho();
                      losingWhoRed = to.getPiece().getWho();
                      losingPic1  = Toolkit.getDefaultToolkit().getImage(fm.getImage());
                      losingPic2  = Toolkit.getDefaultToolkit().getImage(to.getImage());
                      losingRedX = to.getXTop();
                      losingRedY = to.getYTop();
                      losingBluX = to.getXTop();
                      losingBluY = to.getYTop();
                      to.setPiece(null);
                      to.setOccupied(false);
                      to.setExposed(false);
                      to.setMoved(false);
                      fm.setPiece(null);
                      fm.setOccupied(false);
                      fm.setExposed(false);
                      fm.setMoved(false);
                      winner = TieTeam;
                      delayMillis = 50;
                      drawing = true;
                      beepAttrition();
                }
              }
         } // end of moveBluPiece

         // All possible moves are defined and rated; call to this method selects best offensive or defensive move
         // depending on requested parameter...
         // In a situation in which multiple moves have the same best rating, we dont want the same move to be 
         // picked, so two passes are made if there is a tie. A random# picks the move if a tie.
         private nextMove pickMove(int defoff, LinkedList nmL) {
             ListIterator iterator = nmL.listIterator();
             nextMove nm = null;
             nextMove nmBest = null; 
             int max = -2;
             int occurrences = 0;
             int r = 0;
             int r2 = 0;

             while(iterator.hasNext()) {
                    nm = (nextMove) iterator.next();
                    if (defoff == 1) { // offense
                        if (nm.getAttackScore() == max) occurrences++;
                        if (nm.getAttackScore() >  max) {
                            occurrences = 1;
                            max = nm.getAttackScore();
                            nmBest = nm;
                        }
                    }
                    else {           // defense
                        if (nm.getRetreatScore() == max) occurrences++;
                        if (nm.getRetreatScore() >  max) {
                            occurrences = 1;
                            max = nm.getRetreatScore();
                            nmBest = nm;
                        }
                    }
             }

             // randomly select among matching best match
             if (occurrences > 1) {
                 nmBest = null;
                 r = (int) (Math.random() * occurrences);
                 //System.out.print("pickMove - occurrences=" + occurrences + ", r=" + r);
                 //System.out.println(" getPrev max=" + max);
                 while(iterator.hasPrevious() && nmBest == null) {
                       nm = (nextMove) iterator.previous();
                       if (defoff == 1) { // offense
                           //System.out.println(" nm.as=" + nm.getAttackScore() + ", r2=" + r2);
                           if (nm.getAttackScore() == max) {
                               if (r2++ == r) nmBest = nm;
                           }
                       }
                       else {           // defense
                          //System.out.println(" nm.rs=" + nm.getRetreatScore() + ", r2=" + r2);
                          if (nm.getRetreatScore() == max) {
                               if (r2++ == r) nmBest = nm;
                           }
                       }
                 }
             }
              //System.out.println("BluPers=" + BluPersonality + ", " + "this move rnd#=" + offdef);
             System.out.println(nmBest.toCoordString());
             return nmBest;
         }  // end of pickMove

         // If all red/blu moveable pieces are captured - red/blu loses
         private int countMoveable(int t) {
             int count = 0;
             for (int x = 0; x < ROWMAX; x++) {
                 for (int y = 0; y < COLMAX; y++) {
                     if (board[x][y].getTeam() == t && 
                         board[x][y].isMoveable()) {
                         count++;
                     }
                 }
             }
             return count; 
         }

         // This procedure returns a linked list; each nodes containing an object that represents a
         // single valid move for every non-captured moveable blue piece. If no moves are left, null 
         // list is returned 
         private LinkedList buildMoveList() {
             LinkedList moveList = new LinkedList(); 
             nextMove neighbor; 

             resetAdjCnts();

             for (int x = 0; x < ROWMAX; x++) {
                 for (int y = 0; y < COLMAX; y++) {
                     if (board[x][y].getTeam() == BluTeam && 
                         board[x][y].isMoveable()) { 

                         // Certain moves are favored if they cause defusing of bombs
                         findNearestBomb(board[x][y], bombList);

                         neighbor = getMoveInOneDirection(board[x][y], "north");
                         if (neighbor != null)  moveList.add(neighbor);
                         neighbor = getMoveInOneDirection(board[x][y], "west");
                         if (neighbor != null)  moveList.add(neighbor);
                         neighbor = getMoveInOneDirection(board[x][y], "south");
                         if (neighbor != null)  moveList.add(neighbor);
                         neighbor = getMoveInOneDirection(board[x][y], "east");
                         if (neighbor != null)  moveList.add(neighbor);
                         System.out.println(board[x][y].toCoordString() + ": " + board[x][y].getAdjRank());
                     }
                 }
             }
             return moveList;
         }  // end of buildMoveList

         // set every boardSpot that has live moveable blue piece, to point to nearest exposed red bomb 
         private void findNearestBomb(boardSpot bs, LinkedList bL) {
             if (bL.isEmpty()) { //System.out.println("fnb: empty");
                 return; }

             int x2 = 0;
             int y2 = 0;
             int xB = 0;
             int yB = 0;
             int minDist = 99;
             boardSpot nearestBomb = null;
             boardSpot bs2 = null;

             x2 = bs.getOrdX();
             y2 = bs.getOrdY();
             //System.out.println("fnb: for " + x2 + "." + y2);

             ListIterator iterator = bL.listIterator();
             while(iterator.hasNext()) {
                   bs2 = (boardSpot) iterator.next();

                   xB = bs2.getOrdX() - x2;
                   if (xB < 0) xB = - xB;

                   yB = bs2.getOrdY() - y2;
                   if (yB < 0) yB = - yB;

                   if (xB + yB < minDist) {
                       minDist = xB + yB;  
                       nearestBomb = bs2; 
                       //System.out.println("fnb: for " + x2 + "." + y2 + " is " + nearestBomb.toCoordString());
                   } 
             }
             if (nearestBomb != null)
                 bs.setNearestBomb(nearestBomb.getOrdX(), nearestBomb.getOrdY());
         }  // end of findNearestBomb

         // scan all around specified location for spots to which retreat is possible
         private boardSpot checkNeighbor(boardSpot bs, String d) {
             int a = 0;
             int b = 0;
             int x = bs.getOrdX();
             int y = bs.getOrdY();
             int xN = x - 1;
             int yN = y;
             int xE = x;
             int yE = y + 1;
             int xS = x + 1;
             int yS = y;
             int xW = x;
             int yW = y - 1;

             if (d.compareTo("north") == 0) {
                 if (xN < 0) return null;
                 a = xN;
                 b = yN;
             }
             if (d.compareTo("easy") == 0) {
                 if (yE > 9) return null;
                 a = xE;
                 b = yE;
             }
             if (d.compareTo("south") == 0) {
                 if (xS > 9) return null;
                 a = xS;
                 b = yS;
             }
             if (d.compareTo("west") == 0) {
                 if (yW < 0) return null;
                 a = xW;
                 b = yW;
             }
             if (!board[a][b].isLand()) return null;
             if ( board[a][b].isOccupied() && board[a][b].getTeam() == BluTeam) return null;
             if (!board[a][b].isOccupied()) return board[a][b]; 
             if ( board[a][b].isOccupied() && board[a][b].isExposed() &&
                  board[a][b].getPiece().getRank() < board[x][y].getPiece().getRank()) return board[a][b]; 
             return null;
         } // end of checkNeighbor 

         private void resetAdjCnts() {
             for (int x = 0; x < ROWMAX; x++) 
                 for (int y = 0; y < COLMAX; y++) 
                     if (board[x][y].getTeam() == BluTeam) 
                         board[x][y].resetAdj();
         } 

         // This procedure checks a single boardSpot to verify that destination (up/down/left/right) 
         // from the current location is valid for blue piece, no matter how stupid it might be. 
         // It returns a single object that represents that move, or null if that direction isnt valid.
         // Also, set the north/east/south/west properties of the spot to null if retreat cant go there
         // And a 4 char string is set to indicate status of rank for adjacent spots. 
         // WSEN is the offset legend - 1st char=WEST, 2nd char=SOUTH, 3rd char=EAST, 4th char=NORTH
         // "+" means occupied by red, exposed and higher rank than this piece
         // "-" means occupied by red, exposed and lower  rank than this piece
         // "=" means occupied by red, exposed and equal  rank than this piece
         // "?" means occupied by red, hidden
         // "u" means occupied by red, unoccupied 
         // "*" means not valid move 
         private nextMove getMoveInOneDirection(boardSpot bs, String direction) {
              boardSpot bs2 = null;

              if (direction.compareTo("west") == 0) {
                  bs.setAdjRank("west", '*');
                  // Dont allow move off the board 
                  if (bs.getOrdY() == 0) return null;
                  bs2 = board[bs.getOrdX()][bs.getOrdY()-1]; 
                  // Dont allow move to water spot
                  if (!bs2.isLand()) return null;
                  // Dont allow move to spot Blue already holds
                  if (bs2.isOccupied() && (bs2.getTeam() == BluTeam))
                     return null;

                  if (!bs2.isOccupied()) {
                      bs.incrAdjEmpty();  
                      bs.setAdjRank("west", 'u'); }
                  if (bs2.isOccupied() && !bs2.isExposed())
                      bs.setAdjRank("west", '?');
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() > bs.getPiece().getRank()) {
                      bs.incrAdjRedMore(); 
                      bs.setAdjRank("west", '+'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() < bs.getPiece().getRank()) {
                      bs.incrAdjRedLess(); 
                      bs.setAdjRank("west", '-'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() == bs.getPiece().getRank())
                      bs.setAdjRank("west", '=');

                  Dimension f = new Dimension(bs.getOrdX(), bs.getOrdY());
                  Dimension t = new Dimension(bs.getOrdX(), bs.getOrdY() - 1);
                  nextMove nm = new nextMove(bs.getPiece(), bs.getId(), 0, 0, f, t, bs, bs2);
                  return nm;
              }

              if (direction.compareTo("south") == 0) {
                  bs.setAdjRank("south", '*');
                  // Dont allow move off the board 
                  if (bs.getOrdX() == 9) return null;
                  bs2 = board[bs.getOrdX()+1][bs.getOrdY()]; 
                  // Dont allow move to water spot
                  if (!bs2.isLand()) return null;
                  // Dont allow move to spot Blue already holds
                  if (bs2.isOccupied() && (bs2.getTeam() == BluTeam)) return null;

                  if (!bs2.isOccupied()) {
                      bs.incrAdjEmpty();  
                      bs.setAdjRank("south", 'u'); }
                  if (bs2.isOccupied() && !bs2.isExposed())
                      bs.setAdjRank("south", '?');
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() > bs.getPiece().getRank()) {
                      bs.incrAdjRedMore(); 
                      bs.setAdjRank("south", '+'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() < bs.getPiece().getRank()) {
                      bs.incrAdjRedLess(); 
                      bs.setAdjRank("south", '-'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() == bs.getPiece().getRank())
                      bs.setAdjRank("south", '=');

                  Dimension f = new Dimension(bs.getOrdX(), bs.getOrdY());
                  Dimension t = new Dimension(bs.getOrdX()+1, bs.getOrdY());
                  nextMove nm = new nextMove(bs.getPiece(), bs.getId(), 0, 0, f, t, bs, bs2);
                  return nm;
              }

              if (direction.compareTo("east") == 0) {
                  bs.setAdjRank("east", '*');
                  // Dont allow move off the board 
                  if (bs.getOrdY() == 9) return null;
                  bs2 = board[bs.getOrdX()][bs.getOrdY()+1]; 
                  // Dont allow move to water spot
                  if (!bs2.isLand()) return null;
                  // Dont allow move to spot Blue already holds
                  if (bs2.isOccupied() && (bs2.getTeam() == BluTeam)) return null;

                  if (!bs2.isOccupied()) {
                      bs.incrAdjEmpty();  
                      bs.setAdjRank("east", 'u'); }
                  if (bs2.isOccupied() && !bs2.isExposed())
                      bs.setAdjRank("east", '?');
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() > bs.getPiece().getRank()) {
                      bs.incrAdjRedMore(); 
                      bs.setAdjRank("east", '+'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() < bs.getPiece().getRank()) {
                      bs.incrAdjRedLess(); 
                      bs.setAdjRank("east", '-'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() == bs.getPiece().getRank())
                      bs.setAdjRank("east", '=');

                  Dimension f = new Dimension(bs.getOrdX(), bs.getOrdY());
                  Dimension t = new Dimension(bs.getOrdX(), bs.getOrdY()+1);
                  nextMove nm = new nextMove(bs.getPiece(), bs.getId(), 0, 0, f, t, bs, bs2);
                  return nm;
              }
              if (direction.compareTo("north") == 0) {
                  bs.setAdjRank("north", '*');
                  // Dont allow move off the board 
                  if (bs.getOrdX() == 0) return null;
                  bs2 = board[bs.getOrdX()-1][bs.getOrdY()]; 
                  // Dont allow move to water spot
                  if (!bs2.isLand()) return null;
                  // Dont allow move to spot Blue already holds
                  if (bs2.isOccupied() && (bs2.getTeam() == BluTeam)) return null;

                  if (!bs2.isOccupied()) {
                      bs.incrAdjEmpty();  
                      bs.setAdjRank("north", 'u'); }
                  if (bs2.isOccupied() && !bs2.isExposed())
                      bs.setAdjRank("north", '?');
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() > bs.getPiece().getRank()) {
                      bs.incrAdjRedMore(); 
                      bs.setAdjRank("north", '+'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() < bs.getPiece().getRank()) {
                      bs.incrAdjRedLess(); 
                      bs.setAdjRank("north", '-'); }
                  if (bs2.isOccupied() && bs2.isExposed() && bs2.getPiece().getRank() == bs.getPiece().getRank())
                      bs.setAdjRank("north", '=');

                  Dimension f = new Dimension(bs.getOrdX(), bs.getOrdY());
                  Dimension t = new Dimension(bs.getOrdX()-1, bs.getOrdY());
                  nextMove nm = new nextMove(bs.getPiece(), bs.getId(), 0, 0, f, t, bs, bs2);
                  return nm;
              }
              return null;
         } // end of getMoveInOneDirection

         // This procedure walks the linked list of available moves, categorizes attack or not and attack priority 
         private boolean determineOffense(LinkedList nmL, boardSpot bsGoal) {
              boolean dbg = false;
              ListIterator iterator = nmL.listIterator();
              // To determine if Blu can attack an exposed Red piece...
              // Never attack a Marshall, unless Blue piece is a spy (0=default, 100=if spy)
              // Always attack a miner if you can capture it with better rank (100=default)
              // Never attack a bomb unless Blue piece is a miner (0=default, 100=if Miner)
              // Always attack a spy if you can capture it with better rank (100=default, 0=if spy)
              // Always attack flag but since it never moves we never know which piece it is (100=default) 
              // Never attack an exposed piece with own of same rank - it is pointless 
              //     (unless it is their last miner, but not blue's - that is too advanced to code yet)
              // Red's identity........M  Ge  Co  Mj  Ca  Li  Se  Mi   Sc  Sp Bomb Flg
              int[] attackExpFactor = {0, 70, 60, 50, 40, 30, 20, 100, 10, 0, 100, 100};
              int af = 0;
              int cnt = 1;
              boolean offOverride = false; 

              // To determine if Blu can attack a hidden Red piece that has never moved...
              // Blue's identity........M  Ge  Co  Mj  Ca  Li  Se  Mi Sc  Sp B  Fl
              int[] attackHid1Factor = {0,  8, 16, 24, 32, 40, 48, -2, 70, 0, 0, 0};

              // To determine if Blu can attack a hidden Red piece that HAS moved...
              // Blue's identity.........M  Ge  Co  Mj  Ca  Li  Se  Mi Sc  Sp B  Fl
              int[] attackHid2Factor = {10, 20, 30, 40, 50, 60, 70, -2, 90, 0, 0, 0};

              while(iterator.hasNext()) {
                    nextMove nm = (nextMove) iterator.next();
                    boardSpot bsFm = nm.getFmBoardSpot();
                    boardSpot bsTo = nm.getToBoardSpot();
                    //if (bsFm.getPiece().getWho() == IdxMiner) {dbg = true;System.out.print("DO#1:" + bsFm.getPiece().getWho() + "/" + bsFm.toCoordString());}

                    if (bsTo.getTeam() != RedTeam) {
                        if (dbg) System.out.println(bsFm.toCoordString() + "DOa");
                        //System.out.println("DO: " + bsFm.toCoordString() + "&" + bsTo.toCoordString() + "(not/red)");
                        nm.setAttack(false);
                        af = -1;
                    }
                    if (bsTo.getTeam() == RedTeam) {
                       // what do we do if the highestLiveRedRank is lower than this piece's rank? 
                       // We can attack blindly any piece that has already moved 
                       if (dbg) System.out.print(bsFm.toCoordString() + "DOb ");
                       nm.setAttack(true);

                       boardSpot n = checkNeighbor(bsFm, "north"); 
                       boardSpot e = checkNeighbor(bsFm, "east");
                       boardSpot s = checkNeighbor(bsFm, "south");
                       boardSpot w = checkNeighbor(bsFm, "west");

                       if (n != null) { // assigns specific boardSpot as positional neighbor for this move
                           if (dbg) System.out.print(bsFm.toCoordString() + "DOc ");
                           System.out.println(bsFm.toCoordString() + " can retreat to " + n.toCoordString());
                           nm.setRNeighbor("north", n);
                       }
                       if (e != null) {
                           if (dbg) System.out.print(bsFm.toCoordString() + "DOd ");
                           System.out.println(bsFm.toCoordString() + " can retreat to " + e.toCoordString());
                           nm.setRNeighbor("east", e);
                       }
                       if (s != null) { 
                           if (dbg) System.out.print(bsFm.toCoordString() + "DOe ");
                           System.out.println(bsFm.toCoordString() + " can retreat to " + s.toCoordString());
                           nm.setRNeighbor("south", s);
                       }
                       if (w != null) { 
                           if (dbg) System.out.print(bsFm.toCoordString() + "DOf ");
                           System.out.println(bsFm.toCoordString() + " can retreat to " + w.toCoordString());
                           nm.setRNeighbor("west", w);
                       }

                       if (bsTo.isExposed()) {

                           if (dbg) System.out.print(bsFm.toCoordString() + "DOg ");
                           if ((bsFm.getPiece().getWho() == IdxMiner) && (bsTo.getPiece().getWho() == IdxBomb)) {
                               if (dbg) System.out.print(bsFm.toCoordString() + "DOh ");
                               af = 95; offOverride = true; return true;}  // Miner always attacks an exposed bomb
                           if ((bsFm.getPiece().getWho() == IdxSpy) && (bsTo.getPiece().getWho() == IdxMarshall)) {
                               if (dbg) System.out.print(bsFm.toCoordString() + "DOi ");
                               af = 90; offOverride = true; return true;}  // Spy always attacks an exposed Marhsall

                           if (bsFm.getPiece().getRank() > bsTo.getPiece().getRank()) {
                               if (dbg) System.out.print(bsFm.toCoordString() + "DOj ");
                               af = attackExpFactor[bsTo.getPiece().getWho()];
                               if ((bsFm.getPiece().getWho() < IdxMiner) && (bsTo.getPiece().getWho() == IdxMiner)) {
                                   if (dbg) System.out.print(bsFm.toCoordString() + "DOk ");
                                   af = 85; offOverride = true; }  // Better rank than miner always attacks an exposed Miner 
                               if ((bsFm.getPiece().getWho() == IdxSpy) && (bsTo.getPiece().getWho() == IdxSpy)) {
                                   if (dbg) System.out.print(bsFm.toCoordString() + "DOl ");
                                   af = 0;                     } // Spy never attacks an exposed spy
                           }
                           if (bsFm.getPiece().getRank() == bsTo.getPiece().getRank()) {
                                   if (dbg) System.out.print(bsFm.toCoordString() + "DOm ");
                                   af = -3; }  // avoid attacking piece of equal rank - pointless
                           if (bsFm.getPiece().getRank() <  bsTo.getPiece().getRank()) {
                                   if (dbg) System.out.print(bsFm.toCoordString() + "DOn ");
                                   af = -99; } // NEVER attack opponent of known greater rank - stupid
                       }
                       if (!bsTo.isExposed() && !bsTo.hasMoved()) {
                           if (dbg) System.out.print(bsFm.toCoordString() + "DOo ");
                           af = attackHid1Factor[bsFm.getPiece().getWho()];
                       }
                       if (!bsTo.isExposed() && bsTo.hasMoved()) {
                           if (dbg) System.out.print(bsFm.toCoordString() + "DOp ");
                           if (bsFm.getPiece().getRank() > highestLiveRedRank) {
                               if (dbg) System.out.print(bsFm.toCoordString() + "DOq ");
                               af = 95; }// attacking an opponent of unknown rank is not risky if this piece's
                                         // rank is higher than all remaining uncaptured Red pieces
                           else
                           if ((bsFm.getPiece().getWho() == IdxMiner)) {
                               if (dbg) System.out.print(bsFm.toCoordString() + "DOr ");
                               af = -90; } // a miner should never attack opponent that has moved and still of unknown rank
                           else {
                               // attacking an opponent of unknown rank is risky; this table has lower scores
                               if (dbg) System.out.print(bsFm.toCoordString() + "DOs ");
                               af = attackHid2Factor[bsFm.getPiece().getWho()]; }
                       }
                    } // end of RedTeam attack

                    if (bsFm.getPiece().getWho() == IdxMiner) {
                         if (dbg) System.out.print(bsFm.toCoordString() + "DOt " + "to:" + bsTo.isOccupied());
                         if (bsTo.isOccupied() && bsTo.isExposed())
                             if (bsTo.getPiece().getWho() == IdxBomb) {
                                 if (dbg) System.out.print(bsFm.toCoordString() + "DOu ");
                                 af = 95; offOverride = true; } } // Miner always attacks an exposed bomb

                    if (bsFm.getPiece().getWho() == IdxSpy) {
                         if (dbg) System.out.print(bsFm.toCoordString() + "DOv ");
                         if (bsTo.isOccupied() && bsTo.isExposed())
                             if (bsTo.getPiece().getWho() == IdxMarshall) {
                                 if (dbg) System.out.print(bsFm.toCoordString() + "DOw ");
                                 af = 90; offOverride = true; } } // Spy always attacks an exposed Marhsall

                    // miner should move closer to the nearest exposed bomb
                    if ((bsFm.getPiece().getWho() == IdxMiner) && !nm.isAttack() &&
                         bsFm.getNearestBombX() > 0 &&
                         bsTo.getOrdX()         > bsFm.getOrdX() &&      // move closer in the down X direction 
                         bsFm.getNearestBombX() > bsFm.getOrdX() )
                      {  System.out.println(bsFm.toCoordString() + " :1 added 25 for miner movement;" + bsFm.getNearestBombX() + "." + bsFm.getNearestBombX());
                         //if (dbg) System.out.print(bsFm.toCoordString() + "DOx ");
                         af += 25; }
                    else
                    if ((bsFm.getPiece().getWho() == IdxMiner) && !nm.isAttack() &&
                         bsFm.getNearestBombX() > 0 &&
                         bsTo.getOrdX()         < bsFm.getOrdX() &&      // move closer in the down X direction 
                         bsFm.getNearestBombX() < bsFm.getOrdX() )    
                      {  System.out.println(bsFm.toCoordString() + " :2 added 25 for miner movement;" + bsFm.getNearestBombX() + "." + bsFm.getNearestBombX());
                         //if (dbg) System.out.print(bsFm.toCoordString() + "DOx ");
                         af += 25; }
                    else
                    if ((bsFm.getPiece().getWho() == IdxMiner) && !nm.isAttack() &&
                         bsFm.getNearestBombY() > 0 &&
                         bsTo.getOrdY()         > bsFm.getOrdY() &&      // move closer in the down X direction 
                         bsFm.getNearestBombY() > bsFm.getOrdY() )    
                      {  System.out.println(bsFm.toCoordString() + " :3 added 25 for miner movement;" + bsFm.getNearestBombY() + "." + bsFm.getNearestBombY());
                         //if (dbg) System.out.print(bsFm.toCoordString() + "DOx ");
                         af += 25; }
                    else
                    if ((bsFm.getPiece().getWho() == IdxMiner) && !nm.isAttack() &&
                         bsFm.getNearestBombY() > 0 &&
                         bsTo.getOrdY()         < bsFm.getOrdY() &&      // move closer in the down X direction 
                         bsFm.getNearestBombY() < bsFm.getOrdY() )    
                      {  System.out.println(bsFm.toCoordString() + " :4 added 25 for miner movement;" + bsFm.getNearestBombY() + "." + bsFm.getNearestBombY());
                         //if (dbg) System.out.print(bsFm.toCoordString() + "DOx ");
                         af += 25; }

                    // Dont add forward movement factor for miners & spies, when that forward movement would be an attack
                    if (((bsFm.getPiece().getWho() == IdxMiner) || (bsFm.getPiece().getWho() == IdxSpy)) && nm.isAttack())
                         af += 0; // dummy operation
                    else
                    // Dont check the y-direction because pieces will thrash left&right without
                    // getting any closer to the goal
                    if (bsFm.getOrdX()   <  6 &&  
                       (bsTo.getOrdX()   > bsFm.getOrdX() &&   // move closer in the down X direction 
                        bsGoal.getOrdX() > bsFm.getOrdX() )  )
                        {  //System.out.println(bsFm.toCoordString() + " :added 30 for general forward movement");
                        if (dbg) System.out.print(bsFm.toCoordString() + "DOy ");
                        af += 30; }

                    nm.setAttackScore(af);
                    iterator.set(nm);
                    cnt++;
                    if (dbg) System.out.println(" ");
              }
              return offOverride;
         } // end of determineOffense

         // This procedure walks the linked list of available moves, and assigns retreat scores 
         private void determineDefense(LinkedList nmL) {
              // To determine if Blu should retreat...
              // keep it simple for now - miners and spys retreat against UNKNOWN adjacent opponents
              // Blue's identity.........M  Ge  Co  Mj  Ca  Li  Se  Mi  Sc  Sp  B  Fl
              int[] retreatHid1Factor = {0,  0, 10, 15, 20, 25, 30, 80,  0, 85, 0, 0};

              // Only retreat if Blue piece has rank < Red rank (against KNOWN adjacent opponents)
              // Blue's identity.........M  Ge  Co  Mj  Ca  Li  Se  Mi  Sc  Sp  B  Fl
              int[] retreatHid2Factor = {0, 70, 60, 50, 40, 30, 20, 90, 50, 95, 0, 0};

              int df = 0;
              int cnt = 1;
              ListIterator iterator = nmL.listIterator();

              while(iterator.hasNext()) {
                    nextMove nm = (nextMove) iterator.next();
                    boardSpot bsFm = nm.getFmBoardSpot();
                    boardSpot bsTo = nm.getToBoardSpot();

                    if (!nm.isAttack()) 
                         nm.setRetreatScore(0);
                    else { // If there is nowhere to retreat, when a known adjacent opponent has a rank
                           // that can capture this piece, mark it for later removal from the move list.
                           if (!bsTo.isExposed())  {
                               df = retreatHid1Factor[bsFm.getPiece().getWho()];
                               if (bsFm.getAdjRedLess() > 0) {
                                   String x = bsFm.getAdjRank("-");
                                   //System.out.println("GAR1: " + x);
                                   nm.setRetreatBoardSpot(x);
                               }
                               else 
                               if (bsFm.getAdjEmpty() > 0) {
                                   String x = bsFm.getAdjRank("u");
                                   //System.out.println("GAR2: " + x);
                                   nm.setRetreatBoardSpot(x);
                               }
                           }

                           if (bsTo.isExposed()) {
                               df = retreatHid2Factor[bsFm.getPiece().getWho()];
                               if (bsFm.getAdjRedMore() > 0 && bsFm.getAdjEmpty() == 0 && bsFm.getAdjRedLess() == 0) 
                                   { System.out.println("DD:removing a stupid move: " + bsFm.toCoordString() + " to " + bsTo.toCoordString()); 
                                   nmL.remove(bsFm); // nowhere to retreat and stupid to attack; not a valid move 
                                   return;
                                   } 
                               if (bsFm.getAdjRedMore() > 0 && bsFm.getAdjRedLess() > 0) {
                                   // retreat by attacking another of lesser rank 
                                   System.out.println("DD: setup retreat with attack of another piece of lesser rank");
                                   String x = bsFm.getAdjRank("-");
                                   //System.out.println("GAR3: " + x);
                                   nm.setRetreatBoardSpot(x);
                              }
                               if (bsFm.getAdjRedMore() > 0 && bsFm.getAdjRedLess() == 0 && bsFm.getAdjEmpty() > 0) {
                                   // retreat by moving to an unoccupied spot 
                                   System.out.print("DD:found retreat move to open spot ->" + bsFm.toCoordString());
                                   String x = bsFm.getAdjRank("u");
                                   //System.out.println("GAR4: " + x);
                                   nm.setRetreatBoardSpot(x);
                               }                                   
                           }
                    }
                    nm.setRetreatScore(df);
                    iterator.set(nm);
                    cnt++;
              }
         } // end of determineDefense

         private void dbgNextMove(LinkedList nmL) {
              int cnt = 0;
              ListIterator iterator = nmL.listIterator();

              while(iterator.hasNext()) {
                    nextMove nm = (nextMove) iterator.next();
                    boardSpot bsFm = nm.getFmBoardSpot();
                    System.out.print(nm.toString());
                    System.out.println("..." + bsFm.getAdjRank() + 
                                       ";AdjRedLess=" + bsFm.getAdjRedLess() + 
                                       ";AdjRedMore=" + bsFm.getAdjRedMore() + 
                                       ";AdjEmpty="   + bsFm.getAdjEmpty());
              }
         }
         private void dbgBomb() {
              ListIterator iterator = bombList.listIterator();
              boardSpot bs = null;
              int i = 0;
              while(iterator.hasNext()) {
                     bs = (boardSpot) iterator.next();
                     System.out.print("bombList #" + i + ": " + bs.toCoordString());
              }
              System.out.println("bombList end");
         }
         // In determining a goal for direction of attack, we presume the best target is the 
         // biggest block of unmoved pieces.
         private void computeTargetAll() {
               for (int x=6; x<10; x++) 
                    for (int y=0; y<10; y++) { // Red start coordinates
                         board[x][y].setTarget(computeTarget1(x,y)); 
                    }
         }
         private double computeTarget1(int x, int y) {
                 double target = 0.0;

                 int k = x;
                 while (k >= 6 && board[k][y].isOccupied() && !board[k--][y].hasMoved()) target++;
                 //if (x ==6) System.out.println(x + "." + y + " up=" + target);

                 k = x;
                 while (k <= 9 && board[k][y].isOccupied() && !board[k++][y].hasMoved()) target++;
                 //if (x ==6) System.out.println(x + "." + y + " down=" + target);

                 k = y;
                 while (k >= 0 && board[x][k].isOccupied() && !board[x][k--].hasMoved()) target++;
                 //if (x ==6) System.out.println(x + "." + y + " left=" + target);

                 k = y;
                 while (k <= 9 && board[x][k].isOccupied() && !board[x][k++].hasMoved()) target++;
                 //if (x ==6) System.out.println(x + "." + y + " right=" + target);

                 return target / 4.0;
          }

          // Given all the target factors previously computed for this move, find the largest value 
          // and return that to the requestor - all moves will gravitate in that direction for attack
          // Multiple groupings of targets may exist with the same maximum value; doesnt matter which 
          // one we pick for the goal
          private boardSpot determineGoal() {
                 boardSpot bs = null;
                 double max = -1.0;
                 double m = -1.0;

                 for (int x=6; x<=9; x++)
                      for (int y=0; y<=9; y++) {
                           //System.out.println("DetermineGoal: max=" + max); 
                           m = board[x][y].getTarget();
                           if (m > max) {
                               max = m;
                               bs = board[x][y];
                           }
                      }

                 //if (bs == null) System.out.println("null");
                 //else            System.out.println(bs.toCoordString());
                 return bs;
          } // end of determineGoal;

    } // end of AnimationThread
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////   

    public static void main(String args[]) 
    {
         int[] RedMap = {-1, -1, -1, -1, -1, -1, -1, -1, -1,
                         -1, -1, -1, -1, -1, -1, -1, -1, -1,
                         -1, -1, -1, -1, -1, -1, -1, -1, -1,
                         -1, -1, -1, -1, -1, -1, -1, -1, -1};

        // if program was initiated with parameters for RedTeam positions, process it
        if (args.length != 0 && args.length != 2) {
            System.out.println("invalid number of parameters"); 
            System.exit(0);
        }

        if (args.length == 2) {
            if (args[0].compareTo("-Red") != 0) {
                System.out.println("invalid option"); 
                System.exit(0);
            }
            RedMap = validateReds(args[1]);
            if (RedMap[0] == -1) {
                System.out.println("invalid definition of Red Team positions"); 
                System.exit(0);
            }
        }

        final JFrame f = new Stratego(RedMap);

        f.setBounds(1, 1, 1000, 1000);
        f.setBackground(Color.white);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });


    } // end of main

    // Validate the "-Red" startup option string  
    static int[] validateReds(String p) {
         String s;
         int[] map = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
         int[] nbr = {0,0,0,0,0,0,0,0,0,0,0,0};

         //System.out.println("p=" + p); 
         StringTokenizer t = new StringTokenizer(p, ",");
         //System.out.println("#tokens=" + t.countTokens()); 
         if (t.countTokens() != 40) return map;

         for (int i = 0; i < 40; i++) {
              s = t.nextToken();
              //System.out.print("s=" + s); 
              map[i] = Integer.parseInt(s);
              //System.out.println("...map[" + i + "]=" + map[i]); 
              if (map[i] < 0 || map[i] > 11) {
                  map[0] = -1;
                 return(map);
              }
         }

         // Verify proper number of 0,1,2,3,4,5,6,7,8,9,10,11 pieces
         for (int i = 0; i < 40; i++) nbr[map[i]]++;

         for (int i = 0; i < CNTEntities; i++) {
              if (nbr[i] != nbrEntity[i]) 
                  map[0] = -1;
                  return(map);
         }
         return(map);
    } // end validateReds

} // end of class Stratego

class jpanel extends JPanel { 
    jpanel(Color c) { setBackground(c); }
} // end of jpanel

class jbuttonC extends JButton { 
    jbuttonC(Color c) { 
        setBackground(c); 
        setPreferredSize(new Dimension(100, 20)); //80 good for 10x10 grid, but text in buttons is truncated
    }
} // end of jbuttonC

class jlabelC extends JLabel { 
    jlabelC(Color c) { 
        setBackground(c); 
        setPreferredSize(new Dimension(100, 20)); //80 good for 10x10 grid, but text in buttons is truncated
    }
} // end of jlabelC

class jLabelPanel extends JPanel {
    jLabelPanel(Color c) { 
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setBackground(c);
    } // end of jLabelPanel constructor
} // end of jLabelPanel class

class jStrPanel extends JPanel { 
    jStrPanel(Color c) {
      setLayout(new GridLayout(12, 1));
      setBackground(c); 
    } // end of jStrPanel constructor
} // end of jStrPanel class

class jGamePanel extends JPanel { 
    jGamePanel(Color c) {
       setLayout(new GridLayout(10, 10));
       setBackground(c); 
    } // end of jGamePanel constructor
} // end of jGamePanel class

class jMajPanel extends JPanel { 
    jMajPanel(Component pWest, Component pNorth, Component pEast, Component pSouth, Component pCenter) { 
      setBackground(Color.green); 

      setLayout(new BorderLayout(10, 10));

      if (pWest   != null)  add("West",   pWest);
      if (pNorth  != null)  add("North",  pNorth);
      if (pEast   != null)  add("East",   pEast);
      if (pSouth  != null)  add("South",  pSouth);
      if (pCenter != null)  add("Center",  pCenter);

    } // end of jMajPanel constructor

} // end of jMajPanel class

/*-------------------------------------------------------------------------------*/
// This class defines an object that represents a valid next move for Blue
// 
class nextMove {
   // constructor
   public nextMove(piece p, int pNo, int def, int off, Dimension f, Dimension t, 
                   boardSpot bsFm, boardSpot bsTo) {
      this.p = p;
      pieceNbr = pNo;  
      defFactor = def; // range is 0 (not defensive at all) thru 100 (very defensive)
      offFactor = off; // range is 0 (not offensive at all) thru 100 (very offensive)
      fm = f;
      to = t;
      attack = false;
      retreatScore = -1;
      attackScore = -1;
      this.bsFm = bsFm;
      this.bsTo = bsTo;
      bsRetreat = null;
      bsNorth = null;
      bsEast = null;
      bsSouth = null;
      bsEast = null;
   }
   // accessor methods
   public boardSpot getFmBoardSpot() {
       return bsFm;
   }
   public boardSpot getToBoardSpot() {
       return bsTo;
   }
   public boardSpot getRetreatBoardSpot() {
       return bsRetreat;
   }
   public boardSpot getRNeighbor(String dir) {
          if (dir.compareTo("north") == 0) return bsNorth;
          if (dir.compareTo("east")  == 0) return bsEast;
          if (dir.compareTo("south") == 0) return bsSouth;
          if (dir.compareTo("west")  == 0) return bsWest;
          return null;
   }
   public piece getPiece() {
       return p;
   }
   public int getRetreatScore() {
       return retreatScore;   
   }
   public int getAttackScore() {
       return attackScore;   
   }
   public boolean isAttack() {
       return attack;   
   }
   public String toCoordString() {
       String nm = "from " + bsFm.toCoordString() + " " + bsFm.getPiece().toString() + " to " + bsTo.toCoordString();
       return nm;
   }
   public String toString() {
       String nm = "from " + bsFm.toCoordString() + " ";
       nm = nm + bsFm.getPiece().toString() + " to ";
       nm = nm + bsTo.toCoordString();
       if (bsTo.isOccupied()) {
           nm = nm + bsTo.getPiece().toString();
           if (!bsTo.isExposed())
                nm = nm + "(h)";
       }

       String att = attack ? " ->attack" : "";
       
       if (bsRetreat != null)
           nm = nm + ": defense=" + retreatScore + ", to " + bsRetreat.toCoordString() + 
                                                   ", offense="  + attackScore + att;
       else
           nm = nm + ": defense=" + retreatScore + ", offense="  + attackScore + att;

       return nm;
   } 
   // mutator methods
   public void setAttack(boolean a) {
       attack = a;   
   }
   public void setRetreatBoardSpot(String dir) {
          if (dir.compareTo("north") == 0) bsRetreat = bsNorth;
          if (dir.compareTo("east")  == 0) bsRetreat = bsEast;
          if (dir.compareTo("south") == 0) bsRetreat = bsSouth;
          if (dir.compareTo("west")  == 0) bsRetreat = bsWest;
   }
   public void setAttackScore(int s) {
       attackScore = s;   
   }
   public void setRetreatScore(int s) {
       retreatScore = s;   
   }
   public void setRNeighbor(String dir, boardSpot bs) {
          if (dir.compareTo("north") == 0) bsNorth = bs;
          if (dir.compareTo("east")  == 0) bsEast  = bs;
          if (dir.compareTo("south") == 0) bsSouth = bs;
          if (dir.compareTo("west")  == 0) bsWest  = bs;
   }
   private piece p;
   private int pieceNbr;
   private boolean attack;
   private int retreatScore;
   private int attackScore;
   private int defFactor;
   private int offFactor;
   private Dimension fm;
   private Dimension to;
   private boardSpot bsFm;
   private boardSpot bsTo;
   private boardSpot bsRetreat;
   private boardSpot bsNorth;
   private boardSpot bsEast;
   private boardSpot bsSouth;
   private boardSpot bsWest;
} // end of class nextMove

/*-------------------------------------------------------------------------------*/
// This class defines the stratego board piece that is any one of the game board members
//                "who"           "value"
//    Piece        Idx    Count    Rank    String       Image
// IdxMarshall    = 0       1       10     "Marshall"   "_RedMarshall.gif"   or "_BlueMarshall.gif"
// IdxGeneral     = 1       1        9     "General"    "_RedGeneral.gif"    or "_BlueGeneral.gif"
// IdxColonel     = 2       2        8     "Colonel"    "_RedColonel.gif"    or "_BlueColonel.gif"
// IdxMajor       = 3       3        7     "Major"      "_RedMajor.gif"      or "_BlueMajor.gif"
// IdxCaptain     = 4       4        6     "Captain"    "_RedCaptain.gif"    or "_BlueCaptain.gif"
// IdxLieutenant  = 5       4        5     "Lieutenant" "_RedLieutenant.gif" or "_BlueMarshall.gif"
// IdxSeargant    = 6       4        4     "Seargant"   "_RedSeargant.gif"   or "_BlueSeargant.gif"
// IdxMiner       = 7       5        3     "Miner"      "_RedMiner.gif"      or "_BlueMiner.gif"
// IdxScout       = 8       8        2     "Scout"      "_RedScout.gif"      or "_BlueScout.gif"
// IdxSpy         = 9       1        1     "Spy"        "_RedSpy.gif"        or "_BlueSpy.gif"
// IdxBomb        = 10      6       99     "Bomb"       "_RedBomb.gif"       or "_BlueBomb.gif"
// IdxFlag        = 11      1        0     "Flag"       "_RedFlag.gif"       or "_BlueFlag.gif"
class piece {
   // constructor...
   public piece(int i, int t, int w, int v)  {
         id = i;
         team = t;                 // red=0; blue=1
         who = w;                  // index corresponding to string name, and .gif image
         value = v;                // integer value corresponding to rank, needed for battle activity (Marshall=9, ... , Flag=0)
         alive = true;             // captured=dead
         assignedToBoard = false;  // obvious
         moveable = (who >= 10) ? false : true;
         stride = strideTbl[who];
         captured = 0;
     }
     // accessor methods...
     public int getRank() {
          return value;
     }
     public int getTeam() {
          return team;
     }
     public int getId() {
          if (!alive) return -1;
          return id;
     }
     public int getStride() {
          return stride;
     }
     public int getWho() {
          return who;
     }
     public String getImage() {
          return picPiece[team][who];
     }
     public boolean isMoveable() {
          if (!this.alive) return false;
          return moveable;
     }
     public boolean isAssignedToBoard() {
          return assignedToBoard;
     }
     // This function returns true/false to indicate if a piece is allowed to move a specified
     // x,y vector
     public boolean isProperMove(Dimension d) {
        if (d.width >  0 && d.height  > 0) return false;
        if (d.width == 0 && d.height == 0) return false;

        return (d.width <= strideTbl[who] && d.height <= strideTbl[who]);
     } // end of isProperMove

     public String toString() {
        String colorDesc[] = {"Red","Blue"};
        String[] strDesc = {"Marshall","General","Colonel","Major","Captain","Lieutenant",
                            "Seargant","Miner","Scout","Spy","Bomb","Flag"};
        return colorDesc[team] + " " + strDesc[who];
     }
     // mutator methods
     public void init() {
          this.assignedToBoard = false;
          this.alive = true;
          this.captured = 0; 
     }
     public void setAssignedToBoard(boolean b) {
          this.assignedToBoard = b;
     }
     public void incrCaptured() {
          captured++;
     }
     String picPiece[][] = {{"_RedMarshall.gif","_RedGeneral.gif","_RedColonel.gif",
                             "_RedMajor.gif",   "_RedCaptain.gif","_RedLieutenant.gif",
                             "_RedSeargant.gif","_RedMiner.gif",  "_RedScout.gif",
                             "_RedSpy.gif",     "_RedBomb.gif",   "_RedFlag.gif"},
                            {"_BlueMarshall.gif","_BlueGeneral.gif","_BlueColonel.gif",
                             "_BlueMajor.gif",   "_BlueCaptain.gif","_BlueLieutenant.gif",
                             "_BlueSeargant.gif","_BlueMiner.gif",  "_BlueScout.gif",
                             "_BlueSpy.gif",     "_BlueBomb.gif",   "_BlueFlag.gif"}};

     private int id;
     private int strideTbl[] = {1,1,1,1,1,1,1,1,10,1,0,0};
     private int team;
     private int who;
     private int value;
     private int stride;
     private boolean alive;
     private boolean assignedToBoard;
     private boolean moveable;
     private int captured;
} // end of class piece

/*-------------------------------------------------------------------------------*/
class boardSpot {
   // constructor...
   public boardSpot(int xTopLeft, int yTopLeft, int xBotRight, int yBotRight, int x, int y, boolean land, boolean occupied, piece p) {
        this.xTopLeft  = xTopLeft;
        this.yTopLeft  = yTopLeft;
        this.xBotRight = xBotRight;
        this.yBotRight = yBotRight;
        this.ordX      = x;
        this.ordY      = y;  
        this.land      = land;
        this.occupied  = occupied;
        this.p         = p;
        this.startSlot = NoTeam;
        this.exposed   = false;
        this.moved     = false;
        this.target    = 0.0;
        this.trail     = null;
        this.adjRedMore= 0; // adjacent boardSpots that can capture piece here
        this.adjRedLess= 0; // adjacent boardSpots that can be captured by this piece
        this.adjEmpty  = 0; // adjacent boardSpots that can be moved to without attacking it
        this.nearestRedBombX = -1;
        this.nearestRedBombY = -1;
        this.adjacent = "    ";
  }
  // accessor methods...
  public piece getPiece() {      // get the piece object in this board spot (might be null)
      return this.p;
  }
  public int getRank() {         // Get the value of its importance
       return this.p.getRank();
  }
  public String getImage() {     // Get the string that is the file name of the .gif image file
       return this.p.getImage();
  }
  public int getTeam() {         // get the id of the team that occupies this board spot
       if (occupied) return this.p.getTeam();
       else          return -1;
  }
  public int getId() {         // get the id of the piece that occupies this board spot
       if (occupied) return this.p.getId();
       else          return -1;
  }
  public int getStride() {         // get the id of the team that occupies this board spot
       if (occupied) return this.p.getStride();
       else          return -1;
  }
  public int getWho() {          // get the index of the type of piece in this board spot
       if (p == null) {System.out.println("boardSpot.getWho() null piece"); return -1;}
       return this.p.getWho();
  }
  public int getOrdX() {         // get the x coordinate (0..9) within the board
       return this.ordX;
  }
  public int getOrdY() {         // get the y coordinate (0..9) within the board
       return this.ordY;
  }
  public int getXTop() {         // get the topleft corner x coordinate of the board spot, relative to animationPanel
       return this.xTopLeft;
  }
  public int getYTop() {         // get the topleft corner y coordinate of the board spot, relative to animationPanel
       return this.yTopLeft;
  }
  public int getXBot() {         // get the botright corner x coordinate of the board spot, relative to animationPanel
       return this.xBotRight;
  }
  public int getYBot() {         // get the botright corner y coordinate of the board spot, relative to animationPanel 
       return this.yBotRight;
  }
  public int getStartSlot() {    // indicates if this board spot can be assigned a red/blue/none piece at start of game
       return this.startSlot;
  } 
  public double getTarget() {
       return this.target;
  } 
  public int getAdjRedMore() {
       return this.adjRedMore;
  } 
  public int getAdjRedLess() {
       return this.adjRedLess;
  } 
  public int getAdjEmpty() {
       return this.adjEmpty;
  } 
  public int getNearestBombX() {
       return this.nearestRedBombX;
  }
  public int getNearestBombY() {
       return this.nearestRedBombY;
  }
  public boolean isLand() {      // land or water?
       return this.land;
  }
  public boolean isOccupied() {  // occupied by a red or blue piece?
       return this.occupied;
  }
  public boolean isMoveable() {
       if (p == null) return false; 
       return this.p.isMoveable();
  }
  public boolean isExposed() {
       return this.exposed;
  }
  public boolean hasMoved() {
       return this.moved;
  }
  public Image getTrail() {
       if (trail == null) return null;
       return this.trail;
  }
  private String toStartString() {
       int RedTeam = 0;
       int BluTeam = 1;
       if (startSlot == RedTeam) return("RedStartPos");
       if (startSlot == BluTeam) return("BluStartPos");
       return("NotStartPos");
  }
  public String toCoordString() {
       return(this.ordX + "." + this.ordY);
  }
  public String toDiagString() {
       return(this.xTopLeft + "." + this.yTopLeft + "/" + this.xBotRight + "." + this.yBotRight);
  }
  public String toGameStatusString() {
       String status  = exposed ? "exposed" : "hidden"; 
       String transpired = moved ? "moved" : "unmoved"; 
       status = status + "/" + transpired; 
       return(status);
  }
  public String toString() {
       String terrain = land ? "land" : "water";
       String status  = occupied ? p.toString() : "empty"; 
       String display = toCoordString() + "..." + this.toDiagString() + "..." + terrain + "..." + status;
       if (occupied)
           display = display + "..." + toGameStatusString();
       return(display);
  }
  // mutator methods
  public void init(String s) {
       this.p = null;
       this.xTopLeft  = 0;
       this.yTopLeft  = 0;
       this.xBotRight = 0;
       this.yBotRight = 0;
       this.occupied  = false;
       if (s.compareTo("reset") != 0) this.startSlot = NoTeam;
       this.moved = false;
       this.exposed = false;
       this.target = 0.0;
       this.adjRedMore= 0; // adjacent boardSpots that can capture piece here
       this.adjRedLess= 0; // adjacent boardSpots that can be captured by this piece
       this.adjEmpty  = 0; // adjacent boardSpots that can be moved to without attacking it
       this.trail     = null;
       this.nearestRedBombX = -1;
       this.nearestRedBombY = -1;
       this.adjacent = "    ";
  }
  public void incrAdjRedMore() {
       this.adjRedMore++;
  }
  public void incrAdjRedLess() {
       this.adjRedLess++;
  }
  public void incrAdjEmpty() {
       this.adjEmpty++;
  }
  public void resetAdj() {
       this.adjEmpty = 0;
       this.adjRedMore = 0;
       this.adjRedLess = 0;
  }
  public void setStartSlot(int t) {
       this.startSlot = t;
  } 
  public void setNearestBomb(int x, int y) {
       this.nearestRedBombX = x;
       this.nearestRedBombY = y;
  } 
  public void setPiece(piece p) {
       this.p = p;
  }
  public void setLand(boolean l) {
       this.land = l;
  }
  public void setExposed(boolean e) {
       this.exposed = e;
  }
  public void setMoved(boolean m) {
       this.moved = m;
  }
  public void setTarget(double t) {
       this.target = t;
  }
  public void setLocation(int x1, int y1, int x2, int y2) {
       this.xTopLeft  = x1;
       this.yTopLeft  = y1;
       this.xBotRight = x2;
       this.yBotRight = y2;
  }
  public void setOccupied(boolean o) {
       this.occupied = o;
       if (!o)
            this.p = null;
  }   
  public void setTrail(Image i) {
       this.trail = i;
  }
  public void setAdjRank(String dir, char c) {
         //System.out.println("SAR: adjacent='" + adjacent + "'...setting: " + dir + " with " + c);
         if (dir.compareTo("north") == 0) adjacent = adjacent.substring(0,3) + c;
         if (dir.compareTo("east")  == 0) adjacent = adjacent.substring(0,2) + c + adjacent.substring(3,4);
         if (dir.compareTo("south") == 0) adjacent = adjacent.substring(0,1) + c + adjacent.substring(2,4);
         if (dir.compareTo("west")  == 0) adjacent = c + adjacent.substring(1,4);
  }
  public String getAdjRank() {
         //if (dir.compareTo("north") == 0) return adjacent.charAt(3);
         return adjacent;
  }
  public String getAdjRank(String r) {
         //System.out.println("GAR: " + adjacent);
         if (adjacent.substring(0,1).compareTo(r) == 0)
             return("west");
         if (adjacent.substring(1,2).compareTo(r) == 0)
             return("south");
         if (adjacent.substring(2,3).compareTo(r) == 0)
             return("east");
         if (adjacent.substring(3,4).compareTo(r) == 0)
             return("north");
         return("");
  }
  private int ordX;
  private int ordY;
  private int xTopLeft;
  private int yTopLeft;
  private int xBotRight;
  private int yBotRight;
  private int startSlot;
  private boolean land;
  private boolean occupied;
  private piece p;
  private int NoTeam = -1;
  private int RedTeam = 0;
  private boolean exposed;
  private boolean moved;
  private double target = 0.0;
  private Image trail = null;
  private int adjRedMore = 0;  // adjacent boardSpots that can capture piece here
  private int adjRedLess = 0;  // adjacent boardSpots that can be captured by this piece
  private int adjEmpty   = 0;  // adjacent boardSpots that can be moved to without attacking it
  private int nearestRedBombX; // if this boardSpot is occupied by live moveable blue piece, X coordinate of nearest exposed red bomb
  private int nearestRedBombY; // if this boardSpot is occupied by live moveable blue piece, Y coordinate of nearest exposed red bomb
  private String adjacent = "    ";
} // end of class boardSpot

/*-------------------------------------------------------------------------------*/
class Strategy {
  // constructor...
  public Strategy() {
      opponentStrategy = -1;
  }
  // accessor methods...
  public String toString() {
       return strategyTitle[opponentStrategy];
  }
  // mutator methods
  public int[] reset() {
    int r = -1;
    int[] st = new int[40];

    // Make sure reset does not result in same strategy as the prior game setup
    do { r = (int) (Math.random() * 20); }
    while (r == opponentStrategy);

    opponentStrategy = r;
    System.out.println(this.toString());
    return strategyTable[opponentStrategy];
  } // end of reset

  // These 16 tables represent various fixed start-of-game setups for the opponent.
  // The 1st 10 = front row, next 10 = 2nd row, next 10 = 3rd row, last 10 = back row.
  // each number represents the index of the player piece (eg Marshall=0, General=1,...,Flag=11)
  // <20 strategies, with titles>
  private String strategyTitle[] = {"Cyclone Defense","Tempest Defense","Triple Threat","Scout's Gambit",
                                    "On Guard!","Shoreline Bluff","Corner Fortress","Shield Defense",
                                    "Corner Blitz","Wheel of Danger","Blitzkrieg","Early Warning",
                                    "Man the Barricades","Side Sweep","Corner Fake","End Run",
                                    "Hidden Barricade","Bomb Barrier","B29","Miner's Nightmare"};
  //     back row              3rd row                2nd row                 front row
  private int[][] strategyTable = 
  {{3,6,10,6,8,8,5,8,5,8, 9,10,11,10,7,1,5,8,6,4, 0,4,10,7,2,4,7,7,10,6, 8,3,7,8,4,5,3,8,2,10},  // 00-Cyclone Defense OK
   {10,11,10,6,8,5,7,8,7,3, 3,10,5,10,4,7,10,9,5,6, 10,8,8,7,2,4,5,7,0,8, 8,3,1,4,8,6,4,8,2,6},  // 01-Tempest Defense ok
   {10,11,10,10,10,8,5,8,10,7, 5,10,3,8,7,7,3,4,6, 6,4,5,8,8,4,0,9,3,6, 7,8,1,2,4,8,5,7,2,8,6},  // 02-Triple Threat
   {11,10,6,7,8,7,5,8,5,8, 10,4,7,6,10,3,5,9,7,10, 3,10,6,2,8,0,10,8,1,4, 8,5,2,6,8,4,7,4,3,8},  // 03-Scout's Gambit OK
   {6,10,11,10,8,8,6,6,4,8, 1,3,10,7,5,7,4,5,8,7, 8,5,2,9,7,10,5,10,10,2, 8,6,7,0,3,8,4,4,3,8},  // 04-On Guard! OK
   {8,7,6,4,8,8,7,10,4,8, 9,3,5,10,5,7,10,5,3,4, 0,6,2,6,7,4,8,6,7,5, 8,10,11,10,1,8,3,10,2,8},  // 05-Shoreline Bluff OK
   {11,6,10,3,7,6,4,7,5,10, 6,10,2,5,10,4,9,10,8,7, 10,8,3,4,8,8,7,0,7,3, 4,8,1,5,8,2,8,6,5,8},  // 06-Corner Fortress OK
   {7,4,7,10,11,10,3,7,6, 4,7,9,8,8,10,6,7,6,5,10, 3,2,6,4,8,2,3,10,8,4, 5,8,0,8,8,10,5,1,8,5},  // 07-Shield Defense OK
   {11,10,8,10,7,6,10,8,10,7, 10,3,6,3,9,7,6,5,4,7, 5,8,4,2,3,8,8,7,10,0, 8,5,1,4,8,5,4,2,6,8},  // 08-Corner Blitz OK
   {6,8,6,7,10,3,2,9,8,7, 4,2,0,10,11,10,4,7,6,4, 5,10,6,5,10,5,4,10,5,3, 8,8,7,3,8,8,1,8,7,8},  // 09-Wheel of Danger OK
   {8,10,11,10,4,7,8,5,6,6, 3,8,10,8,3,7,6,10,9,5, 5,7,7,8,10,8,4,8,3,10, 8,2,4,6,0,1,4,5,2,7},  // 10-Blitzkrieg OK
   {8,6,10,11,10,7,5,4,4,7, 4,10,5,10,7,6,8,4,7,5, 3,10,9,3,6,2,3,1,7,8, 8,6,0,8,8,10,8,2,5,8},  // 11-Early Warning OK
   {11,0,8,6,8,7,8,2,7,8, 6,6,4,7,5,6,7,4,8,7, 10,10,5,3,10,10,3,9,10,10, 3,5,4,1,8,8,2,5,8,4},  // 12-Man the Barricades OK
   {11,10,5,7,10,6,7,4,8,5, 10,4,8,5,6,10,4,8,7,8, 3,8,2,8,4,5,8,2,8,9, 0,3,7,6,10,10,6,7,3,1},  // 13-Side Sweep OK
   {10,10,7,11,7,9,8,7,10,10, 10,8,8,0,8,7,8,8,7,10, 3,2,8,4,1,3,4,8,2,3, 5,6,4,5,6,6,5,4,6,5},  // 14-Corner Fake OK
   {8,6,7,5,8,9,6,10,5,7, 5,6,4,4,7,7,4,8,4,5, 3,1,2,10,0,3,10,7,2,3, 8,8,10,11,10,10,6,8,8,8},  // 15-End Run OK
   {11,3,4,8,8,8,5,8,7,7, 5,9,4,6,4,7,8,4,8,8, 10,10,0,2,10,10,2,1,10,10, 6,7,5,3,6,8,5,3,6,7},  // 16-Hidden Barricade OK
   {11,10,3,7,3,4,10,7,8,5, 10,7,9,3,4,10,7,4,5,8, 2,0,8,2,10,5,8,5,8,7, 4,8,1,10,6,6,8,8,6,6},  // 17-Bomb Barrier OK
   {11,10,7,7,7,4,8,8,4,8, 10,3,7,7,2,3,8,3,6,8, 2,9,10,4,6,8,8,10,5,4, 10,1,0,8,6,5,6,5,5,10},  // 18-B29 OK
   {11,10,6,10,4,7,7,7,5,3, 10,6,10,8,8,5,6,4,8,4, 6,10,7,4,3,0,8,8,3,8, 10,1,9,2,8,5,7,2,8,5}}; // 19-Miner's Nightmare
   private int opponentStrategy = -1;
} // end of class Strategy
/*-------------------------------------------------------------------------------*/

