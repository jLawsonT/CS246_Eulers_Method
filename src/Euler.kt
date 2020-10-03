// Euler's method is a computational method used to numerically solve first order differential equations
// This code will solve a projectile flight path subject to air drag using Euler's method.

const val FMT = " %7.3f"

// Global Variables
val ro = 1.0    // density of air +- 0.1 kg/m^3
val Cd = 0.47   // drag coefficient of circle
val m = .1      // Arbitrary weight
val A = .3      // Cross sectional area
val v = 5       // Initial velocity
val theta = 75 * 3.1415926/180   // launch angle
val vx = v * java.lang.Math.cos(theta)
val vy = v * java.lang.Math.sin(theta)

typealias Deriv = (Double) -> Double

fun euler(f: Deriv, x: Double, step: Int, end: Int) {
    var xx = x
    var step1 = 0.0
    if (step==1){
        step1 = 0.1
    }
    if (step==2){
        step1 = 0.01
    }
    else {
        step1 = 0.001
    }
    //var step1 = step/100
    print(" Step %2d: ".format(step))
    for (t in 0..100) {   // end step step.toInt()
        if (t % 10 == 0) print(FMT.format(xx))
        xx += step1/100 * f(xx)
        //t += step1
    }
    println()
}

fun flightdx(dx: Double) = -.5 * ro * A * Cd * java.lang.Math.sqrt( vx*vx + vy*vy ) * vx
fun flightdy(dy: Double) = -.5 * ro * A * Cd * java.lang.Math.sqrt( vx*vx + vy*vy ) * vy

fun main(args: Array<String>) {
    //analytic()

    for (i in listOf(1, 2, 3)) {
        euler(::flightdx, 0.0, i as Int, 3)
        //euler(::flightdy, 100, i as Int, 3)
    }
}
