You are given 2 input files planets & characters (JSON & XML) which contain the data for the program.
Each character has an initial health, and attack stats, and a flag indicating if this character is a hero or villain.
Each planet contains health & attack modifiers for villains and heroes, that are applied before the fight begins.

At runtime, the user should be able to select from the console/UI a planet, a villain and a superhero.
After this the fight begins, and the villain and hero attack turn by turn until someone's health reaches 0.
The damage that a character does each round is random, and it varies from 60% to 100% of his attack stats.

BONUS REQUIREMENT(optional):
Since most villains are more powerful than heroes, you could allow the user to create an 'Avengers' team, that contain multiple heroes to defend a villain.