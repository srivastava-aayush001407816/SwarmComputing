# Particle Swarm Optimization

The particle swarm optimization was originally presented by Kennedy and Eberhart, it has been compared
with Genetic Algorithm, it is inspired by social behavior among individuals, these individuals are moving
through an n-dimensional search space, each particle represents a potential solution of the problem, and
can remember the best position it has reached. All the particles can share their information about the
search space, so there is a global best solution. In each of iteration, every particle calculates its velocity
according to the formula.

The basic PSO model consists of a swarm of particles, which are initialized with a population of random
candidate solutions. They move iteratively through the d-dimension problem space to search for the new
solutions, where the fitness, f, can be calculated as the certain qualities measure. Each particle has a
position represented by a position-vector xi (i is the index of the particle), and a velocity represented by a
velocity-vector vi. Each particle remembers its own best position so far in a vector i-th, and its d-
dimensional value is pbest(pid). The best position-vector among the swarm so far is then stored in the
vector i-th, and its d-th dimensional value is gbest(pgd).

# Running Instructions
- Clone the project
- Download Required JARS
- Put the jars in class path
- Run the project
