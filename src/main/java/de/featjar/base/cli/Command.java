/*
 * Copyright (C) 2022 Sebastian Krieter, Elias Kuiter
 *
 * This file is part of util.
 *
 * util is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * util is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with util. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-util> for further information.
 */
package de.featjar.base.cli;

import de.featjar.base.extension.Extension;
import java.util.List;
import java.util.Optional;

/**
 * A command run within a {@link CommandLine}.
 *
 * @author Sebastian Krieter
 * @author Elias Kuiter
 */
public interface Command extends Extension {
    /**
     * {@return this command's name}
     */
    default String getName() {
        return getIdentifier();
    }

    /**
     * {@return this command's description}
     */
    default Optional<String> getDescription() {
        return Optional.empty(); // todo: optional or null?
    }

    /**
     * {@return this command's usage}
     */
    default Optional<String> getUsage() {
        return Optional.empty();
    }

    /**
     * Runs this command.
     *
     * @param args the arguments
     */
    void run(List<String> args);
}
