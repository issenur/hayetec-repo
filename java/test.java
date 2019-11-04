public class test{
	public static void main(String[] args){
		int current = 1;
		int size = 7;
		int random = (int)(Math.random()*(size - 2)) + current;
		
		System.out.println(random);
	}
}
                                                                                
/*                                                                              
                                                                                
Recall the definition of the Traveling Salesperson Problem (the TSP):           
Given a set of cities, you want to find the shortest route that visits every city 
and ends up back at the original starting city.                                 
                                                                                
                                                                                
For the purposes of this problem, every city will be directly reachable from    
every other city (think flying from city to city).                              
                                                                                
                                                                                
                                                                                
Your goal is to use a non-genetic local search algorithm                        
                                                                                
or algorithms from Chapter 4 of Poole & Mackworth,                              
                                                                                
or something else that you find in the literature, to find the                  
shortest paths that you can.                                                    
                                                                                
                                                                                
                                                                                
                                                                                
Note that for any reasonably sized problem, you won’t be able to try every      
possibility, and so you won’t ever know when you have the shortest possible     
path.                                                                           
                                                                                
                                                                                
                                                                                
Verbose Mode should print to a file (and also, if you wish, to the console)     
every path you try until your program terminates.                               
                                                                                
                                                                                
In all cases, print the single-letter mnemonic of the city (Node),              
not its full name.                                                              
                                                                                
                                                                                
                                                                                
                                                                                
Start from any city you want, it really doesn’t matter which. I start from      
the first city in the file because it’s easiest.                                
                                                                                
Create some tour.                                                               
                                                                                
    You can do this randomly, you can use a greedy algorithm                    
    of some sort,                                                               
                                                                                
    you can just visit the cities in the order they appear in the               
    file, whatever.                                                             
                                                                                
                                                                                
    Your choice probably won’t affect the goodness of your final tour,          
    but it might affect how long it takes you to get to a good solution.        
                                                                                
                                                                                
Using the techniques of local search, try to find a better tour.                
If you want to use a local search algorithm from the literature, go ahead.      
                                                                                
Be sure to document what you’re trying to do.  If you have any questions about  
whether the algorithm qualifies as a “local search” algorithm, please ask me.   
                                                                                
The rest of this section assumes you want to create your own algorithm.         
Here are some thoughts on what                                                  
I think is the main question: how to step from one possible tour to the next.   
                                                                                
    One option would be two swap the order of two cities and see if this        
    shortens the tour                                                           
    Mpls. -> Chicago -> Detroit -> Boston -> Seattle -> Miami -> Denver -> Mpls.  becomes
    Mpls. -> Seattle -> Detroit -> Boston -> Chicago -> Miami -> Denver -> Mpls.
                                                                                
                                                                                
    Or you could pick one city in the tour, and try removing it and inserting it 
    between every pair of cities to see which gives the shortest tour.          
                                                                                
    There are other forms of Iterative Best Improvement, too.                   
                                                                                
                                                                                
    When deciding what cities to swap or what city to shuffle,                  
    you could use either a 1-stage or 2-stage choice algorithm.                 
                                                                                
With any sort of iterative best improvement, there are various techniques       
you could use if you wish.                                                      
                                                                                
                                                                                
One of the big differences among peoples’ algorithms will be their choices      
to use or not use these techniques, and the algorithm they use to decide        
when to use them (how often to do a random step or restart, temperatures        
for simulated annealing, etc.)                                                  
                                                                                
                                                                                
    You could choose to use Simulated Annealing to decide whether to keep a     
    given tour even if it’s longer than the existing tour.                      
                                                                                
    You could inject randomness by sometimes making random                      
    permutations (random walk).                                                 
                                                                                
    You could decide after a certain number of iterations that you should       
    just start over from scratch with a random order of cities (random restart).  
                                                                                
    You could choose to keep a tabu list or not,                                
     and if you do you could choose its size.                                   
                                                                                
    You could keep multiple tours, and permute them in parallel (beam search).  
                                                                                
                                                                                
Since you don’t know when you have an optimal tour for any reasonably           
sized problem, you need to decide on a stopping criterion.                      
Some possible suggestions:                                                      
Stop after some number of steps (where the number of steps might be some        
function of the number n of cities).                                            
                                                                                
    Stop after you have gone some number k of steps without improving           
    on your best answer.                                                        
                                                                                
    Stop after the program has run for some amount of real-time                 
     (measured by something like System.time.millis()).                         
                                                                                
                                                                                
                                                                                
Report                                                                          
You should write a brief report that explains what you did.                     
It should indicate the algorithm you chose, and any parameters you used for     
it.  You should discuss the good ness of the results you seemed to get.         
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
Output:                                                                         
Here is sample output for one graph C5.txt.  First, here is the file.           
~               Val    C    H    K    M    P                                    
Cleveland         C    ~ 1114  700  630  360                                    
Houston           H 1114    ~  644 1056 1341                                    
KansasCity        K  700  644    ~  413 1039                                    
Minneapolis       M  630 1056  413    ~  985                                    
Philadelphia      P  360 1341 1039  985    ~                                    
Suppose that you do the following:                                              
    • Start out by traveling the cities in this order:  Cleveland, Kansas City, Houston, Minneapolis, Philadelphia, back to Cleveland.
    • Switch the positions of Houston and Kansas City                           
    • Switch the positions of Kansas City and Philadelphia                      
    • Switch the positions of Kansas City and Minneapolis                       
    • Terminate                                                                 
The verbose output would look like this.                                        
CKHMPC 3745 (random restart)                                                    
CHKMPC 3116 (swap)                                                              
CPHMKC 3870 (swap)                                                              
CPHKMC 3388 (swap)                                                              
                                                                                
Shortest path found was CHKMPC with distance 3116 after 4 steps.                
*/                                                                              
/*                                                                              
The summary output would look like this, only printing the improved tours.      
CKHMPC 3745                                                                     
CHKMPC 3116                                                                     
Shortest path found was CHKMPC with distance 3116 after 4 steps.                
The summary output for graph <name>.txt should be written to file <name>S.txt, and may be written to the console, too.  The verbose output should be written to file <name>V.txt.
Submit:                                                                         
Submit the Java source code to the open Deliverable C submission folder. You may submit either the source code or a full Eclipse package, as with Deliverable A.
Test Files:                                                                     
There are five test files that will be used for grading: CD5.txt, CD7.txt, CD10.txt, CD15.txt, and CD49.txt.  
Grading:                                                                        
60 points for passing the tests “correctly” – you get good answers using a reasonable algorithm in a reasonable amount of time.  Yes, there is some subjectivity to this.  There are five files at 10 points each, plus 10 points for Deliverable A functionality not being broken; it will be tested on one of the files.  10 points each for design and documentation.  20 points for the report.
Due Dates:                                                                      
The program is due on Friday, March 22nd, at noon for full credit in the D2L “Deliverable C” dropbox.   For 80% of credit earned, you may (re)submit it by Friday, April 5th. The time of submission is the time that D2L lists the file as submitted.
                                                                                
*/                                                                                                                                                            

                                                                                                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                                                                                                            

