package com.example.central_dogma;

import com.fasterxml.jackson.databind.JsonNode;
import com.linecorp.centraldogma.client.CentralDogma;
import com.linecorp.centraldogma.client.Watcher;
import com.linecorp.centraldogma.common.Entry;
import com.linecorp.centraldogma.common.Query;
import com.linecorp.centraldogma.common.Revision;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public class FileReadCentralDogmaAdapter implements FileReadPort{

    private final CentralDogma centralDogma;
    private final QueryHolder queryHolder;

    @Override
    public void initFile(String projectName, String repositoryName, String fileName) {
        log.info("=========== init cache by central dogma ===========");
        CompletableFuture<Entry<JsonNode>> file =
                centralDogma.getFile(projectName, repositoryName, Revision.HEAD, Query.ofJsonPath(fileName));
        Entry<JsonNode> jsonNodeEntry = file.join();
        JsonNode content = jsonNodeEntry.content();
        log.info("{}", content);
        queryHolder.initCache("demo", content.asText());
    }

    @Override
    public void watchFile(String projectName, String repositoryName, String fileName) {
        log.info("listen file changed");
        Watcher<JsonNode> watcher =
                centralDogma.fileWatcher("demo", "demo",
                        Query.ofJsonPath("/demo.json"));
        // Register a callback for changes.
        watcher.watch((revision, value) -> {
            queryHolder.updateCache("demo", value.asText());
        });
    }
}
