package br.com.desafioandroidenjoei.testing

/**
 * This annotation allows us to open some classes for mocking purposes while they are final in
 * release builds.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting